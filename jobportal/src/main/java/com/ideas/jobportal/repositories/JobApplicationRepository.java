package com.ideas.jobportal.repositories;

import com.ideas.jobportal.models.Job;
import com.ideas.jobportal.models.JobApplication;
import com.ideas.jobportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication , Long> {
  List<JobApplication> findByJob(Job job);
  List<JobApplication> findByApplicant(User applicant);
  JobApplication findByJobAndApplicant(Job job  , User applicant);
}
