package com.ideas.jobportal.models;
import jakarta.persistence.*;
import java.util.Date;



@Entity
public class Experience {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "company_name")
  private String companyName;
  private String description;

  @Column(name = "start_date")
  private Date startDate;
  @Column(name = "end_date")
  private Date endDate;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Experience() {
  }

  public Experience(String companyName, String description, Date startDate, Date endDate , User user) {
    this.companyName = companyName;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getDescription() {
    return description;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public User getUser() {
    return user;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
