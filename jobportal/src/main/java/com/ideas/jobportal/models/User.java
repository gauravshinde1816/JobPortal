package com.ideas.jobportal.models;
import jakarta.persistence.*;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;

    private String password;

    private  String role;

    private  String bio;

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getCurrent_company() {
    return current_company;
  }

  public void setCurrent_company(String current_company) {
    this.current_company = current_company;
  }

  private  String current_company;

    public User() {
    }

    public User(String username, String email, String password, String role , String bio ,String current_company) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.bio = bio;
        this.current_company = current_company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public  String getRole(){
        return  role;
    }


    public  void setRole(String role){
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
