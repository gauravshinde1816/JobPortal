package com.ideas.jobportal.models.payload;

public class JwtResponse {


    private  String username;

    private  String jwtToken;


    public JwtResponse(String username, String jwtToken) {
        this.username = username;
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
