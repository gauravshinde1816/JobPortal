package com.ideas.jobportal.services;

import com.ideas.jobportal.models.Experience;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.ExperienceRepository;
import com.ideas.jobportal.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExperienceServiceTest {

  @Mock
  private  ExperienceRepository experienceRepository;

  @Mock
  private UserRepository userRepository;


  @InjectMocks
  private  ExperienceService experienceService;


  @Test
  void givenExperienceRequestObject_whenAddExperience_thenReturnExperienceObject() throws ParseException {

    User user = new User();
    user.setUsername("testUser");


    Authentication authentication = mock(Authentication.class);
    when(authentication.getName()).thenReturn("testUser");
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = dateFormat.parse("2022-06-120");
    Date endDate = dateFormat.parse("2023-07-25");

    Experience experience = new Experience(
      "UBS" , "Sample Description" ,  startDate , endDate , user
    );

    given(experienceRepository.save(experience)).willReturn(experience);
    Experience newExperience = experienceService.addExperience(experience);


    Assertions.assertNotNull(newExperience);

  }

  @Test
  void getExperiences() throws ParseException {
    User user = new User();
    user.setUsername("testUser");


    //setting up the authentication
    Authentication authentication = mock(Authentication.class);
    when(authentication.getName()).thenReturn("testUser");
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = dateFormat.parse("2022-06-120");
    Date endDate = dateFormat.parse("2023-07-25");

    Experience experience_1 = new Experience(
      "UBS" , "Sample Description" ,  startDate , endDate , user
    );

    Date startDate_2 = dateFormat.parse("2021-06-120");
    Date endDate_2 = dateFormat.parse("2023-07-2");

    Experience experience_2 = new Experience(
      "Barclays" , "Sample Description 2" ,  startDate_2, endDate_2, user
    );

    given(experienceRepository.findByUser(user)).willReturn(List.of(experience_1 , experience_2));
    List<Experience> experienceList  = experienceService.getExperiences();



    Assertions.assertNotNull(experienceList);
    assertEquals(experienceList.size() , 2);

  }

  @Test
  void updateExperience() throws ParseException {

    User user = new User();
    user.setUsername("testUser");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = dateFormat.parse("2022-06-120");
    Date endDate = dateFormat.parse("2023-07-25");

    Experience oldExperience = new Experience(
      "UBS" , "Sample Description" ,  startDate , endDate , user
    );
    oldExperience.setId(2L);

    given(experienceRepository.findById(2L)).willReturn(Optional.of(oldExperience));
    given(experienceRepository.save(oldExperience)).willReturn(oldExperience);

    oldExperience.setCompanyName("Barclays");
    oldExperience.setDescription("Sample Description 2");

    Experience newExperience  = experienceService.updateExperience(oldExperience.getId() , oldExperience);


    Assertions.assertNotNull(newExperience);
    Assertions.assertEquals(newExperience.getCompanyName() , "Barclays");
    Assertions.assertEquals(newExperience.getDescription() ,  "Sample Description 2");

  }

  @Test
  void deleteExperience() throws ParseException {
    User user = new User();
    user.setUsername("testUser");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = dateFormat.parse("2022-06-120");
    Date endDate = dateFormat.parse("2023-07-25");

    Experience experience = new Experience(
      "UBS" , "Sample Description" ,  startDate , endDate , user
    );
    experience.setId(2L);

    doNothing().when(experienceRepository).deleteById(experience.getId());
    given(experienceRepository.findById(2L)).willReturn(Optional.of(experience));


    experienceService.deleteExperience(experience.getId());
    verify(experienceRepository , times(1)).deleteById(experience.getId());

  }
}
