package com.ideas.jobportal.controller;


import com.ideas.jobportal.models.JobApplication;
import com.ideas.jobportal.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/applications")

public class JobApplicationController {

  @Autowired
  JobApplicationService jobApplicationService;

  @GetMapping("/me")
  public List<JobApplication> getJobApplicationForLoggedUser(){
    return jobApplicationService.findJobApplicationsForLoggedInuser();
  }

  @GetMapping("/{job_id}/list")
  public List<JobApplication> getJobApplicationsForJob(@PathVariable Long job_id){
    return jobApplicationService.findJobApplicationsForJob(job_id);
  }


  @GetMapping("/{job_id}/applied/me")
  public JobApplication getJobApplicationForJobByLoggedInUser(@PathVariable Long job_id){
    return jobApplicationService.findJobApplicationForJobByLoggedInUser(job_id);
  }

  @PostMapping("/{job_id}/apply")
  public JobApplication createNewJobApplication(@PathVariable Long job_id) {
    return jobApplicationService.createNewJobApplication(job_id);
  }

  @PutMapping("/{application_id}/status")
  public JobApplication changeStatusOfApplication(@PathVariable Long application_id){
      return  jobApplicationService.changeStatusOfJobApplication(application_id);
  }

  @DeleteMapping("/{application_id}")
  public void deleteJobApplication(@PathVariable Long application_id){
     jobApplicationService.deleteJobApplication(application_id);
  }

}
