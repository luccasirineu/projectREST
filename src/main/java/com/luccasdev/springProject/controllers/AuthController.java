package com.luccasdev.springProject.controllers;

import com.luccasdev.springProject.data.dto.v1.AccountCredentialsDTO;
import com.luccasdev.springProject.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login Endpoint")
@RequestMapping(value = "/auth")
public class AuthController {
    
    @Autowired
    AuthService authService;

    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/login")
    public ResponseEntity login (@RequestBody AccountCredentialsDTO userDTO){
        if(checkUsernamePassowordIsNotNull(userDTO))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        var token = authService.login(userDTO);

        if(token == null)  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        return token;
    }

    public boolean checkUsernamePassowordIsNotNull(AccountCredentialsDTO userDTO){
        return userDTO == null || userDTO.getUsername() == null || userDTO.getUsername().isBlank()
                || userDTO.getPassword() == null || userDTO.getPassword().isBlank();
    }
}
