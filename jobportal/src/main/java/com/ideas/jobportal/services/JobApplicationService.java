package com.ideas.jobportal.services;

import com.ideas.jobportal.models.Job;
import com.ideas.jobportal.models.JobApplication;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.JobApplicationRepository;
import com.ideas.jobportal.repositories.JobRepository;
import com.ideas.jobportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobApplicationService {

  @Autowired
  JobApplicationRepository jobApplicationRepository;

  @Autowired
  JobRepository jobRepository;

  @Autowired
  UserRepository userRepository;


  @Autowired
  UserService userService;

  public JobApplication createNewJobApplication(Long jobID) {
    //  Get the job
    Job job = jobRepository.findById(jobID).get();

    //   Get the Logged In user : applicant
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User applicant = userRepository.findByUsername(authentication.getName()).get();

    // Set status to true
    Boolean status = true;

    JobApplication newJobApplication = new JobApplication(
      job,
      applicant,
      status
    );

    return jobApplicationRepository.save(newJobApplication);
  }

  public JobApplication changeStatusOfJobApplication(Long application_id) {
    JobApplication jobApplication = jobApplicationRepository.findById(application_id).get();
    jobApplication.setStatus(false);
    jobApplicationRepository.save(jobApplication);
    return jobApplication;
  }


  public List<JobApplication> findJobApplicationsForLoggedInuser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User applicant = userRepository.findByUsername(authentication.getName()).get();
    return jobApplicationRepository.findByApplicant(applicant);
  }

  public JobApplication findJobApplicationForJobByLoggedInUser(Long job_id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User applicant = userRepository.findByUsername(authentication.getName()).get();
    Job job = jobRepository.findById(job_id).get();
    return jobApplicationRepository.findByJobAndApplicant(job , applicant);
  }



  public List<JobApplication> findJobApplicationsForJob(Long job_id) {
    //  Get the job
    Job job = jobRepository.findById(job_id).get();
    return jobApplicationRepository.findByJob(job);
  }


  public void deleteJobApplication(Long application_id) {
    jobApplicationRepository.deleteById(application_id);
  }


}
