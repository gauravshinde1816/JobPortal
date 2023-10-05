package com.ideas.jobportal.services;
import com.ideas.jobportal.models.Experience;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.ExperienceRepository;
import com.ideas.jobportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {


  @Autowired
  UserRepository userRepository;

  @Autowired
  ExperienceRepository experienceRepository;


  public Experience addExperience(Experience experienceRequest) {

    //Get Logged User
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(authentication.getName()).get();

    Optional<Experience> ExistingExperience = experienceRepository.findByCompanyNameAndUser( experienceRequest.getCompanyName() , user);

    if(ExistingExperience.isPresent()){
      return  null;
    }

    Experience NewExperience = new Experience(
      experienceRequest.getCompanyName(),
      experienceRequest.getDescription(),
      experienceRequest.getStartDate(),
      experienceRequest.getEndDate(),
      user
    );

    experienceRepository.save(NewExperience);
    return  NewExperience;
  }

  public List<Experience> getExperiences(){
    //Get Logged User
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(authentication.getName()).get();
    return experienceRepository.findByUser(user);
  }

  public Experience updateExperience(Long experience_id ,  Experience experienceRequest){
    Optional<Experience> existingExperience = experienceRepository.findById(experience_id);

    if(existingExperience.isPresent()){

      Experience experience = existingExperience.get();
      experience.setCompanyName(experienceRequest.getCompanyName());
      experience.setDescription(experienceRequest.getDescription());
      experience.setStartDate(experienceRequest.getStartDate());
      experience.setEndDate(experienceRequest.getEndDate());
      experienceRepository.save(experience);
      return  experience;
    }
    return  null;
  }



  public ResponseEntity<?> deleteExperience(Long experience_id) {
    Optional<Experience> experience = experienceRepository.findById(experience_id);

    if (experience.isPresent()) {
      experienceRepository.deleteById(experience_id);
      String msg = "Experience " + experience_id + " is deleted successfully";
      return ResponseEntity.ok(msg);
    }
    return ResponseEntity.notFound().build();
  }



}
