package com.ideas.jobportal.JobTests;


import com.ideas.jobportal.models.Job;
import com.ideas.jobportal.models.JobApplication;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.JobApplicationRepository;
import com.ideas.jobportal.repositories.JobRepository;
import com.ideas.jobportal.repositories.UserRepository;
import com.ideas.jobportal.services.JobApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JobApplicationTests {

  @Mock
  UserRepository userRepository;
  @Mock
  JobRepository jobRepository;
  @InjectMocks
  JobApplicationService jobApplicationService;
  @Mock
  private JobApplicationRepository jobApplicationRepository;

  @Test
  public void givenJobApplicationObject_ReturnsNewJobApplication() {

    //applicant for  job
    User applicant = new User();
    applicant.setUsername("TestUser");
    applicant.setRole("JS");
    //hiring Manager for job

    Authentication authentication = mock(Authentication.class);
    when(authentication.getName()).thenReturn("TestUser");
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(userRepository.findByUsername("TestUser")).thenReturn(Optional.of(applicant));


    //  Create new Job
    //hiring manager  for  job
    User hiring_manager = new User();
    applicant.setUsername("TestUser2");
    applicant.setRole("HM");

    Job newJob = new Job("SDE 1", "Sample Description 1", "SOFTWARE", "Uplers", hiring_manager);
    newJob.setJob_id(1L);




    //create new job application
    //current job application status
    Boolean status = true;

    JobApplication newJobApplication = new JobApplication(newJob, applicant, status);


    given(jobApplicationRepository.save(newJobApplication)).willReturn(newJobApplication);
    given(jobRepository.findById(newJob.getJob_id())).willReturn(Optional.of(newJob));

    JobApplication jobApplication = jobApplicationService.createNewJobApplication(newJob.getJob_id());


    Assertions.assertNotNull(jobApplication);
    Assertions.assertEquals(jobApplication, newJobApplication);

  }


}
