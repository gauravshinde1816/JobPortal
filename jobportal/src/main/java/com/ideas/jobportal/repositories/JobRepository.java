package com.ideas.jobportal.repositories;

import com.ideas.jobportal.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository  extends JpaRepository<Job, Long> {
}
