package com.ideas.jobportal.controller;
import com.ideas.jobportal.models.Education;
import com.ideas.jobportal.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@CrossOrigin(
  origins = {
    "http://localhost:4200",
  },
  methods = {
    RequestMethod.OPTIONS,
    RequestMethod.GET,
    RequestMethod.PUT,
    RequestMethod.DELETE,
    RequestMethod.POST
  })

@RestController
@RequestMapping("/api/educations")
public class EducationController {

  @Autowired
  EducationService educationService;


  @GetMapping("")
  public List<Education> getEducationsForLoggedUser(){
    return  educationService.getEducationForLoggedUser();
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
  public  ResponseEntity<?> deleteEducation(@PathVariable Long education_id){
       return educationService.deleteEducation(education_id);
  }

}
