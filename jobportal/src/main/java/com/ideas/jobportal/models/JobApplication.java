package com.ideas.jobportal.models;
import jakarta.persistence.*;

import java.util.Objects;


@Entity(name = "JobApplication")
public class JobApplication {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "application_id")
  private Long ID;


  @ManyToOne
  @JoinColumn(name = "job_details")
  private Job job;

  @OneToOne
  @JoinColumn(name = "applicant")
  private User applicant;

  @Column(name = "")
  private Boolean status;


  public JobApplication() {
  }

  public JobApplication(Job job, User applicant, Boolean status) {
    this.job = job;
    this.applicant = applicant;
    this.status = status;
  }


  public Long getID() {
    return ID;
  }

  public Job getJob() {
    return job;
  }

  public User getApplicant() {
    return applicant;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setID(Long ID) {
    this.ID = ID;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  public void setApplicant(User applicant) {
    this.applicant = applicant;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JobApplication that = (JobApplication) o;
    return Objects.equals(getID(), that.getID()) && Objects.equals(getJob(), that.getJob()) && Objects.equals(getApplicant(), that.getApplicant()) && Objects.equals(getStatus(), that.getStatus());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getID(), getJob(), getApplicant(), getStatus());
  }


}
