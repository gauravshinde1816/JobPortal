package com.ideas.jobportal.security;

import com.ideas.jobportal.jwt.AuthTokenFilter;
import com.ideas.jobportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.util.Arrays;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

  @Autowired
  UserService userService;

  @Bean
  public AuthTokenFilter authJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }


  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(auth ->
        auth.requestMatchers("/api/auth/**").permitAll()
          .requestMatchers("/api/educations/**").permitAll()
          .requestMatchers("/api/experiences/**").permitAll()
          .requestMatchers("/api/applications/**").permitAll()
          .requestMatchers("/api/notifications").permitAll()
          .requestMatchers("/api/jobs/**").permitAll()
          .anyRequest().authenticated()
      );
    http.authenticationProvider(authenticationProvider());
    http.addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

}
