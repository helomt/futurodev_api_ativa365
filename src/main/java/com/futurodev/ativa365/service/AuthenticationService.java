package com.futurodev.ativa365.service;

import com.futurodev.ativa365.model.transport.LoginForm;
import com.futurodev.ativa365.model.transport.TokenDTO;
import com.futurodev.ativa365.security.JwtAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final AuthenticationManager authenticationManager;


    public AuthenticationService(JwtAuthenticationProvider jwtAuthenticationProvider, AuthenticationManager authenticationManager) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.authenticationManager = authenticationManager;
    }

    public TokenDTO login(LoginForm form){
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.username(), form.password()));
        return this.jwtAuthenticationProvider.authenticate(form.username());
    }

    public TokenDTO refreshToken(String refreshToken){
        if (refreshToken == null){
            throw new IllegalArgumentException("Refresh token is required for this operation");
        }
        return this.jwtAuthenticationProvider.refreshToken(refreshToken);
    }


}
