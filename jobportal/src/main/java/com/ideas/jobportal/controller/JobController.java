package com.ideas.jobportal.controller;
import com.ideas.jobportal.models.Job;
import com.ideas.jobportal.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(
  origins = {
    "http://localhost:4200",
  },
  methods = {
    RequestMethod.OPTIONS,
    RequestMethod.GET,
    RequestMethod.PUT,
    RequestMethod.DELETE,
    RequestMethod.POST
  })

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
  public Job updateJob(@PathVariable Long job_id, @RequestBody Job updatedJob) {
    return jobService.updateJob(job_id, updatedJob);
  }

  @DeleteMapping("/{job_id}")
  public ResponseEntity<?> deleteJob(@PathVariable Long job_id) {
    return jobService.deleteJob(job_id);
  }

}
