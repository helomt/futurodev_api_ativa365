package com.futurodev.ativa365.model.transport;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "Classe para armazenar um Token de acesso")
public class TokenDTO {

    @Schema(description = "Nome de usuário cadastrado/ email do usuário", example = "john.doe@exemplo.com" )
    private String username;
    @Schema(description = "Booleano para verificação de autenticação", example = "true")
    private Boolean authenticated;
    @Schema(description = "Horário de criação do token", example = "2024-08-18T12:23:43.142+00:00" )
    private Date createdAt;
    @Schema(description = "Horário de criação do token", example = "2024-08-18T13:23:43.142+00:00" )
    private Date expiresAt;
    @Schema(description = "Horário de criação do token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." )
    private String accessToken;
    @Schema(description = "Horário de criação do token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." )
    private String refreshToken;

    public TokenDTO(String username,
                    Boolean authenticated,
                    Date createdAt,
                    Date expiresAt,
                    String accessToken,
                    String refreshToken){

        this.username = username;
        this.authenticated = authenticated;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
