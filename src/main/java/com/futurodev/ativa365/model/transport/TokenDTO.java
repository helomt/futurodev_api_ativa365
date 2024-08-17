package com.futurodev.ativa365.model.transport;

import java.util.Date;

public class TokenDTO {

    private String username;
    private Boolean authenticated;
    private Date createdAt;
    private Date expiresAt;
    private String accessToken;
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
