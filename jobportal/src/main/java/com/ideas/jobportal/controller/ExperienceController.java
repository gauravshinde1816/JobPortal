package com.ideas.jobportal.controller;
import com.ideas.jobportal.models.Education;
import com.ideas.jobportal.models.Experience;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.UserRepository;
import com.ideas.jobportal.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {
  @Autowired
  ExperienceService experienceService;


  @Autowired
  UserRepository userRepository;
  @GetMapping("")
  public List<Experience> getExperiences() {
    return experienceService.getExperiences();
  }

  @GetMapping("/{user_id}")
  public List<Experience>getExperiencesForUser(@PathVariable Long user_id){
    User user = userRepository.findById(user_id).get();
    return  experienceService.getExperienceByUser(user);
  }


  @PostMapping("")
  public Experience addExperience(@RequestBody Experience experienceRequest) {
    return experienceService.addExperience(experienceRequest);
  }


  @PutMapping("/{experience_id}")
  public Experience updateExperience(@PathVariable Long experience_id, @RequestBody Experience experienceRequest) {
    return experienceService.updateExperience(experience_id, experienceRequest);
  }


  @DeleteMapping("/{experience_id}")
  public void deleteExperience(@PathVariable Long experience_id) {
      experienceService.deleteExperience(experience_id);
  }

}
