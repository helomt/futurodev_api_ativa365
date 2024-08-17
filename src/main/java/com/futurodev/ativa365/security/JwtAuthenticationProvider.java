package com.futurodev.ativa365.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.futurodev.ativa365.exceptions.InvalidJwtAuthenticationException;
import com.futurodev.ativa365.model.transport.TokenDTO;
import com.futurodev.ativa365.repository.PersonRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Service
public class JwtAuthenticationProvider {

    private final Long validityInMilliseconds;

    private final PersonRepository personRepository;

    private final Algorithm algorithm;

    public JwtAuthenticationProvider(@Value("${security.jwt.token.expire-length:3600000}") Long validityInMilliseconds,
                                     PersonRepository personRepository,
                                     @Value("${security.jwt.token.secret-key:secret}")String secretKey) {
        this.validityInMilliseconds = validityInMilliseconds;
        this.personRepository = personRepository;
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        this.algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    private DecodedJWT configureDecodedToken(String token){
        JWTVerifier jwtVerifier = JWT.require(this.algorithm).build();
        return jwtVerifier.verify(token);
    }

    private String configureRefreshToken(String username, Date currentDate){
        Date newExpiredAtWithRefreshToken = new Date(currentDate.getTime() + (this.validityInMilliseconds*3));
        return JWT.create()
                .withClaim("roles", new ArrayList<>())
                .withIssuedAt(currentDate)
                .withExpiresAt(newExpiredAtWithRefreshToken)
                .withSubject(username)
                .sign(this.algorithm)
                .strip();
    }

    private String configureAccessToken(String username, Date currentDate, Date expiresAt){
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles", new ArrayList<>())
                .withIssuedAt(currentDate)
                .withExpiresAt(expiresAt)
                .withSubject(username)
                .withIssuer(issuerUrl)
                .sign(this.algorithm)
                .strip();
    }


    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = this.configureDecodedToken(token);
        String username = Objects.requireNonNull(decodedJWT).getSubject();
        UserDetails userDetails = this.personRepository
                .findByEmailAndDeletedFalse(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String removeTokenPrefix(String token){
        String prefix = "Bearer ";
        if(token != null && (token.startsWith(prefix) || token.contains(prefix))){
            return token.replace(prefix, "");
        }
        return token;
    }

    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = this.removeTokenPrefix(token);
        return token;
    }

    public boolean validateToken(String token){
        DecodedJWT decodedJWT = this.configureDecodedToken(token);
        try{
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e){
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
    public TokenDTO authenticate(String username){
        UserDetails userDetails = this.personRepository.findByEmailAndDeletedFalse(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Date currentDate = new Date();
        Date expiresAt = new Date(currentDate.getTime() +this.validityInMilliseconds);
        String accessToken = this.configureAccessToken(userDetails.getUsername(), currentDate, expiresAt);
        String refreshToken = this.configureRefreshToken(userDetails.getUsername(), currentDate);
        return new TokenDTO(
                userDetails.getUsername(),
                true,
                currentDate,
                expiresAt,
                accessToken,
                refreshToken
        );
    }

    public  TokenDTO refreshToken(String refreshToken){
        refreshToken = this.removeTokenPrefix(refreshToken);
        JWTVerifier jwtVerifier = JWT.require(this.algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
        return this.authenticate(decodedJWT.getSubject());
    }


}
