package com.ideas.jobportal.models;

public class JobNotification {
  private String message;

  public JobNotification() {
  }

  public JobNotification(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
