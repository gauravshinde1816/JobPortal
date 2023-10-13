package com.ideas.jobportal.services;

import com.ideas.jobportal.models.APIError;
import com.ideas.jobportal.models.Job;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.JobRepository;
import com.ideas.jobportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JobService {

  @Autowired
  JobRepository jobRepository;
  @Autowired
  UserRepository userRepository;


  public List<Job> getAllJobs(){
    return  jobRepository.findAll();
  }


  public  List<Job> getAllJobsByHiringManager(){

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(authentication.getName()).get();
    return jobRepository.findByHiringManager(user);
  }



  public Job createJob(Job job_request) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User hiring_manager = userRepository.findByUsername(authentication.getName()).get();


    Job new_job = new Job(
      job_request.getJob_title(),
      job_request.getJob_description(),
      job_request.getJob_category(),
      job_request.getCompany_details(),
      hiring_manager
    );
    return  jobRepository.save(new_job);

  }

  public ResponseEntity<?> getJobByID(Long job_id) {
    Optional<Job> job = jobRepository.findById(job_id);
    if (job.isPresent()) return ResponseEntity.ok(job);
    else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }


  public Job updateJob(Long job_id , Job updatedJob){
    Optional<Job> job = jobRepository.findById(job_id);
    if (job.isPresent()) {
      Job existingJob = job.get();

      existingJob.setJob_category(updatedJob.getJob_category());
      existingJob.setJob_description(updatedJob.getJob_description());
      existingJob.setCompany_details(updatedJob.getCompany_details());
      existingJob.setJob_title(updatedJob.getJob_title());

      jobRepository.save(existingJob);

      return existingJob;
    }
    return null;
  }


  public void deleteJob(Long job_id) throws Exception {
    Optional<Job> job = jobRepository.findById(job_id);
    if (job.isPresent()) {
      Job existingJob = job.get();
      jobRepository.deleteById(job_id);}
    else{
      throw new Exception("Job not found");
    }
  }
}
