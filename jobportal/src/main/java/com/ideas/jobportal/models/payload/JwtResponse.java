package com.ideas.jobportal.models.payload;

import com.ideas.jobportal.models.User;

public class JwtResponse {


    private User user;

    private  String jwtToken;


    public JwtResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setUsername(User user) {
        this.user = user;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
