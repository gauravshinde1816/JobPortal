package com.ideas.jobportal.models;


import jakarta.persistence.*;


@Entity
public class Job {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long job_id;
  private  String job_title;
  private  String job_description;
  private String job_category;
  private String company_details;
  @ManyToOne
  @JoinTable(name = "hiring_manager")
  private User hiring_manager;

  public Job(Long job_id, String job_title, String job_description, String job_category, String company_details, User hiring_manager) {
    this.job_id = job_id;
    this.job_title = job_title;
    this.job_description = job_description;
    this.job_category = job_category;
    this.company_details = company_details;
    this.hiring_manager = hiring_manager;
  }

  public Long getJob_id() {
    return job_id;
  }

  public String getJob_title() {
    return job_title;
  }

  public String getJob_description() {
    return job_description;
  }

  public String getJob_category() {
    return job_category;
  }

  public String getCompany_details() {
    return company_details;
  }

  public User getHiring_manager() {
    return hiring_manager;
  }


  public void setJob_id(Long job_id) {
    this.job_id = job_id;
  }

  public void setJob_title(String job_title) {
    this.job_title = job_title;
  }

  public void setJob_description(String job_description) {
    this.job_description = job_description;
  }

  public void setJob_category(String job_category) {
    this.job_category = job_category;
  }

  public void setCompany_details(String company_details) {
    this.company_details = company_details;
  }

  public void setHiring_manager(User hiring_manager) {
    this.hiring_manager = hiring_manager;
  }
}
