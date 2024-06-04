package com.luccasdev.springProject.services;

import com.luccasdev.springProject.data.dto.v1.AccountCredentialsDTO;
import com.luccasdev.springProject.data.dto.v1.TokenDTO;
import com.luccasdev.springProject.repositories.UserRepository;
import com.luccasdev.springProject.security.TokenJwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TokenJwtProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity login (AccountCredentialsDTO userDTO){
        try {
            var username = userDTO.getUsername();
            var password = userDTO.getPassword();
            // tentando realizar o login com o username e password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username);

            var tokenResponse = new TokenDTO();

            // criando um accessToken caso o user exista
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            }else{
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);


        }catch (Exception ex){
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
