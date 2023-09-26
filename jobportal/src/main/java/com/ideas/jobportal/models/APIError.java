package com.ideas.jobportal.models;

import org.springframework.http.HttpStatus;

public class APIError {

    private HttpStatus status;
    private  String error;
    private String path;

  public APIError( String error) {
    this.status = status;
    this.error = error;
  }
}
