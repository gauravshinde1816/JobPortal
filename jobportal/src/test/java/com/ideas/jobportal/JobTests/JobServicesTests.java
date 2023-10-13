package com.ideas.jobportal.JobTests;
import com.ideas.jobportal.models.Job;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.JobRepository;
import com.ideas.jobportal.repositories.UserRepository;
import com.ideas.jobportal.services.JobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JobServicesTests {



  @Mock
  private JobRepository jobRepository;
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private JobService jobService;

  private  Job job;
  @Test
  void givenJobList_whenGetAllJobs_thenReturnJobsList(){
    Job job1 = new Job("SDE 1" ,  "Sample Description 1" , "SOFTWARE" ,  "Uplers"  ,  new User());
    Job job2 = new Job("SDE 2" ,  "Sample Description 2" , "SOFTWARE" ,  "IDeaS"  ,  new User());


    given(jobRepository.findAll()).willReturn(List.of(job1 ,  job2));


    List<Job> jobList = jobService.getAllJobs();

    Assertions.assertNotNull(jobList);
    assertEquals(jobList.size() , 2);
  }


  @Test
  void givenJobId_whenGetJobById_thenReturnJobObject(){
      Job job1 = new Job("SDE 1" ,  "Sample Description 1" , "SOFTWARE" ,  "Uplers"  ,  new User());
      job1.setJob_id(1L);
      given(jobRepository.findById(1L)).willReturn(Optional.of(job1));
      ResponseEntity<?> job = jobService.getJobByID(1L);
      Assertions.assertNotNull(job);
  }


  @Test
  void givenJobRequestObject_whenCreateJob_thenReturnJobObject(){
    Job newJob = new Job();
    User user = new User();
    user.setUsername("testUser");

    newJob.setJob_title("SDE");
    newJob.setJob_description("Sample Description");
    newJob.setCompany_details("IdeaS");
    newJob.setJob_category("SOFTWARE");
    newJob.setHiring_manager(user);

    given(jobRepository.save(newJob)).willReturn(newJob);



    Authentication authentication = mock(Authentication.class);
    when(authentication.getName()).thenReturn("testUser");
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));


    Job job = jobService.createJob(newJob);

    Assertions.assertNotNull(job);
    assertEquals(job , newJob);

  }


  @Test
  void givenJobObject_whenUpdateJob_thenReturnUpdatedJob(){

    User user = new User();
    user.setUsername("TestUser");

    Job newJob = new Job("SDE 1" ,  "Sample Description 1" , "SOFTWARE" ,  "Uplers"  , user);
    newJob.setJob_id(2L);

    given(jobRepository.save(newJob)).willReturn(newJob);
    when(jobRepository.findById(2L)).thenReturn(Optional.of(newJob));

    newJob.setJob_title("SDE2");
    newJob.setJob_category("SOFTWARE MANAGER");
    newJob.setCompany_details("IDeaS");

    Job updatedJob = jobService.updateJob(newJob.getJob_id() ,  newJob);

    assertEquals(updatedJob.getJob_title() , "SDE2");
    assertEquals(updatedJob.getJob_category() ,  "SOFTWARE MANAGER");

  }

  @Test
  void givenJobID_whenDeleteJob_thenReturnSuccessString() throws Exception {
      User user = new User();
      user.setUsername("TestUser");

     Job newJob = new Job("SDE 1" ,  "Sample Description 1" , "SOFTWARE" ,  "Uplers"  , user);
     newJob.setJob_id(2L);
     when(jobRepository.findById(2L)).thenReturn(Optional.of(newJob));
     doNothing().when(jobRepository).deleteById(newJob.getJob_id());

      jobService.deleteJob(newJob.getJob_id());
      verify(jobRepository , times(1)).deleteById(newJob.getJob_id());

  }



}
