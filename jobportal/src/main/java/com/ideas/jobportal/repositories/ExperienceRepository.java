package com.ideas.jobportal.repositories;

import com.ideas.jobportal.models.Education;
import com.ideas.jobportal.models.Experience;
import com.ideas.jobportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience ,  Long> {

  Optional<Experience> findByCompanyName(String name);
  List<Experience> findByUser(User user);

  Optional<Experience> findByCompanyNameAndUser(String name , User user);
}
