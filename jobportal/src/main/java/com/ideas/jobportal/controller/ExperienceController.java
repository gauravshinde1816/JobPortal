package com.ideas.jobportal.controller;
import com.ideas.jobportal.models.Experience;
import com.ideas.jobportal.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {
  @Autowired
  ExperienceService experienceService;


  @GetMapping("")
  public List<Experience> getExperiences() {
    return experienceService.getExperiences();
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
  public ResponseEntity<?> deleteExperience(@PathVariable Long experience_id) {
     return  experienceService.deleteExperience(experience_id);
  }

}
