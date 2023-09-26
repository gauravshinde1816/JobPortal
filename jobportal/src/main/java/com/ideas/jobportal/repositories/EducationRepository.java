package com.ideas.jobportal.repositories;

import com.ideas.jobportal.models.Education;
import com.ideas.jobportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education ,  Long> {
  Optional<Education> findByInstituteName(String name);
  List<Education> findByUser(User user);

  Optional<Education> findByInstituteNameAndUser(String name , User user);
}
