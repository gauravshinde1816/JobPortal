package com.ideas.jobportal.controller;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.models.payload.JwtResponse;
import com.ideas.jobportal.models.payload.LoginRequest;
import com.ideas.jobportal.models.payload.SignUpRequest;
import com.ideas.jobportal.repositories.UserRepository;
import com.ideas.jobportal.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@CrossOrigin(
  origins = {
    "http://localhost:4200",
  },
  methods = {
    RequestMethod.OPTIONS,
    RequestMethod.GET,
    RequestMethod.PUT,
    RequestMethod.DELETE,
    RequestMethod.POST
  })

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @Autowired
  PasswordEncoder encoder;


  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignUpRequest signupRequest) {

    //  checks to be added
    User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()), signupRequest.getRole());

    userRepository.save(user);
    String jwt = Jwts.builder().setSubject(signupRequest.getUsername()).setIssuedAt(new Date())
      .signWith(SignatureAlgorithm.HS256, "======================Secret=Spring===========================")
      .compact();


    return ResponseEntity.ok(new JwtResponse(
      user,
      jwt
    ));

  }


  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = Jwts.builder().setSubject(loginRequest.getUsername()).setIssuedAt(new Date())
      .signWith(SignatureAlgorithm.HS256, "======================Secret=Spring===========================")
      .compact();

    //get logged in user
    User user = userRepository.findByUsername(authentication.getName()).get();
    return ResponseEntity.ok(new JwtResponse(
      user,
      jwt
    ));
  }

  @GetMapping("/me")
  public User getLoggedInUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(authentication.getName()).get();
    return user;
  }
}
