package com.ideas.jobportal.models;
import jakarta.persistence.*;
import java.util.Objects;


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
  @JoinColumn(name = "hiring_manager")
  private User hiringManager;

  public Job() {
  }

  public Job(String job_title, String job_description, String job_category, String company_details, User hiring_manager) {

    this.job_title = job_title;
    this.job_description = job_description;
    this.job_category = job_category;
    this.company_details = company_details;
    this.hiringManager = hiring_manager;
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
    return hiringManager;
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
    this.hiringManager = hiring_manager;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Job job = (Job) o;
    return Objects.equals(getJob_id(), job.getJob_id()) && Objects.equals(getJob_title(), job.getJob_title()) && Objects.equals(getJob_description(), job.getJob_description()) && Objects.equals(getJob_category(), job.getJob_category()) && Objects.equals(getCompany_details(), job.getCompany_details()) && Objects.equals(getHiring_manager(), job.getHiring_manager());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getJob_id(), getJob_title(), getJob_description(), getJob_category(), getCompany_details(), getHiring_manager());
  }

  @Override
  public String toString() {
    return "Job{" +
      "job_id=" + job_id +
      ", job_title='" + job_title + '\'' +
      ", job_description='" + job_description + '\'' +
      ", job_category='" + job_category + '\'' +
      ", company_details='" + company_details + '\'' +
      ", hiring_manager=" + hiringManager +
      '}';
  }
}
