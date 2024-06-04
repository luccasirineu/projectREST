package com.luccasdev.springProject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    TokenJwtProvider tokenJwtProvider;

    public JwtTokenFilter(TokenJwtProvider tokenJwtProvider) {
        this.tokenJwtProvider = tokenJwtProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = tokenJwtProvider.resolveToken((HttpServletRequest)request); // obtendo token
        if (token != null && tokenJwtProvider.validateToken(token)) { // validando token
            Authentication authentication = tokenJwtProvider.getAuthentication(token); // obtem autenticação
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication); // setando a autenticação no ContextHolder do Spring
            }
        }
        chain.doFilter(request, response);

    }
}
