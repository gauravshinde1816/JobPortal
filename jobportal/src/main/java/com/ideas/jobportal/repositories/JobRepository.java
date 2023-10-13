package com.ideas.jobportal.repositories;

import com.ideas.jobportal.models.Education;
import com.ideas.jobportal.models.Job;
import com.ideas.jobportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository  extends JpaRepository<Job, Long> {
  List<Job> findByHiringManager(User user);

}
