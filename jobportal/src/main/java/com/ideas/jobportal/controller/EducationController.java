package com.ideas.jobportal.controller;
import com.ideas.jobportal.models.Education;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.UserRepository;
import com.ideas.jobportal.services.EducationService;
import com.ideas.jobportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/educations")
public class EducationController {

  @Autowired
  EducationService educationService;

  @Autowired
  UserRepository userRepository;


  @GetMapping("")
  public List<Education> getEducationsForLoggedUser(){
    return  educationService.getEducationForLoggedUser();
  }

  @GetMapping("/{user_id}")
  public List<Education> getEducationsForUser(@PathVariable Long user_id){
    User user = userRepository.findById(user_id).get();
    return  educationService.getEducationByUser(user);
  }


  @PostMapping("")
  public ResponseEntity<?> addEducationForUser(@RequestBody Education educationRequest) {
    return  educationService.addEducation(educationRequest);

  }

  @PutMapping("/{education_id}")
  public  ResponseEntity<?> updateEducation(@PathVariable Long education_id , @RequestBody Education updatedEducationRequest){
     return  educationService.updateEducation(education_id ,  updatedEducationRequest);
  }

  @DeleteMapping("/{education_id}")
  public  void deleteEducation(@PathVariable Long education_id){
      educationService.deleteEducation(education_id);
  }
}
