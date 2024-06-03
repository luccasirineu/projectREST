package com.luccasdev.springProject.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.luccasdev.springProject.data.dto.v1.TokenDTO;
import com.luccasdev.springProject.exceptions.InvalidJwtAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;


public class TokenJwtProvider {
    // caso nao exista no application.yml ele ira setar esses valores como padrao
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:36000000}")
    private long validtyInMiliseconds = 36000000;


    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct // ele fa a ação logo apos a inicializacao do spring, algo similar ao beforeAll nos testes
    protected void init(){

        //encoding
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAccessToken(String username, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime() + validtyInMiliseconds);
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getRefreshToken(username, roles, now);
        return new TokenDTO(username, true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create()
                .withClaim("roles",roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(algorithm);

    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        Date validityRefreshToken = new Date(now.getTime() + (validtyInMiliseconds * 3));
        return JWT.create()
                .withClaim("roles",roles)
                .withIssuedAt(now)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(algorithm);

    }

    //decoding
    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
       Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        return verifier.verify(token);
    }

    public String resolveToken(HttpServletRequest req){
        String bearerToken = req.getHeader("Authorization");

        if(bearerToken != null ){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            return !decodedJWT.getExpiresAt().before(new Date());
        }catch (Exception ex){
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
