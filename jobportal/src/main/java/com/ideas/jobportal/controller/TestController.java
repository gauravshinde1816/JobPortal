package com.ideas.jobportal.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {


  @GetMapping("")
  public String  checkAuthAPI( ){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return  authentication.getPrincipal().toString();
  }
}
