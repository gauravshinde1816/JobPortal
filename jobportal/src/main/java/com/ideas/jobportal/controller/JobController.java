package com.ideas.jobportal.controller;

import com.ideas.jobportal.models.Job;
import com.ideas.jobportal.repositories.JobRepository;
import com.ideas.jobportal.repositories.UserRepository;
import com.ideas.jobportal.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

  @Autowired
  JobService jobService;

  @GetMapping("")
  public List<Job> getJobs() {
    return jobService.getAllJobs();
  }

  @GetMapping("/{job_id}")
  public ResponseEntity<?> getJobByID(@PathVariable Long job_id) {
    return jobService.getJobByID(job_id);
  }

  @PostMapping("")
  public Job createNewJob(@RequestBody Job job_request) {
    return jobService.createJob(job_request);
  }


  @PutMapping("/{job_id}")
  public ResponseEntity<?> updateJob(@PathVariable Long job_id, @RequestBody Job updatedJob) {
    return jobService.updateJob(job_id, updatedJob);
  }

  @DeleteMapping("/{job_id}")
  public ResponseEntity<?> deleteJob(@PathVariable Long job_id) {
    return jobService.deleteJob(job_id);
  }

}
