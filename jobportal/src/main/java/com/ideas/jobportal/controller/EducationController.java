package com.ideas.jobportal.controller;


import com.ideas.jobportal.models.Education;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.EducationRepository;
import com.ideas.jobportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/educations")
public class EducationController {


  @Autowired
  EducationRepository educationRepository;

  @Autowired
  UserRepository userRepository;


  @GetMapping("")
  public List<Education> getEducationForLoggedUser(){

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(authentication.getName()).get();

    return educationRepository.findByUser(user);
  }

  @PostMapping("")
  public ResponseEntity<?> addEducation(@RequestBody Education educationRequest) {

    //Get Logged User
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(authentication.getName()).get();

    Optional<Education> ExistingEducation = educationRepository.findByInstituteNameAndUser(educationRequest.getInstituteName() , user);

    if(ExistingEducation.isPresent()){
      return  ResponseEntity.badRequest().build();
    }

    Education NewEducation = new Education(
      educationRequest.getInstituteName(),
      educationRequest.getDescription(),
      educationRequest.getStartDate(),
      educationRequest.getEndDate(),
      user
    );

    educationRepository.save(NewEducation);
    return  ResponseEntity.ok(NewEducation);
  }



  @PutMapping("/{education_id}")
  public  ResponseEntity<?> updateEducation(@PathVariable Long education_id , @RequestBody Education updatedEducationRequest){

    Optional<Education> edu = educationRepository.findById(education_id);

    if(edu.isPresent()) {
      Education existingEducation = edu.get();

      existingEducation.setDescription(updatedEducationRequest.getDescription());
      existingEducation.setInstituteName(updatedEducationRequest.getInstituteName());
      existingEducation.setStartDate(updatedEducationRequest.getStartDate());
      existingEducation.setEndDate(updatedEducationRequest.getEndDate());
      educationRepository.save(existingEducation);
      return ResponseEntity.ok(existingEducation);
    }
    return ResponseEntity.notFound().build();
  }


  @DeleteMapping("/{education_id}")

  public  ResponseEntity<?> deleteEducation(@PathVariable Long education_id){
    Optional<Education> education = educationRepository.findById(education_id);

    if(education.isPresent()){
      educationRepository.deleteById(education_id);
      String msg = "Job " + education_id + " is deleted successfully";
      return  ResponseEntity.ok(msg);
    }
     return  ResponseEntity.notFound().build();
  }

}
