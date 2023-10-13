package com.ideas.jobportal.security;

import jakarta.servlet.ServletException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @Override
  protected void successfulAuthentication(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain,
    Authentication authResult) throws IOException, ServletException {

    // Add CORS headers to the response
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200"); // Replace with your Angular app's URL
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
    response.setHeader("Access-Control-Allow-Credentials", "true");

    // Continue with the filter chain
    super.successfulAuthentication(request, response, chain, authResult);
  }
}
