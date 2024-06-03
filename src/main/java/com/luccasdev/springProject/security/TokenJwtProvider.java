package com.luccasdev.springProject.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.luccasdev.springProject.data.dto.v1.TokenDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;

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

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAccessToken(String username, List<String> roles){
        Date now = new Date();
        Date validity = new Date(now.getTime() + validtyInMiliseconds);
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getAccessToken(username, roles, now);
        return new TokenDTO(username, true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        return null;
    }

    private String getAccessToken(String username, List<String> roles, Date now) {
        return null;
    }
}
