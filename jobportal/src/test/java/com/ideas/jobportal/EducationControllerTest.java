package com.ideas.jobportal;

import com.ideas.jobportal.controller.EducationController;
import com.ideas.jobportal.models.Education;
import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.EducationRepository;
import com.ideas.jobportal.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EducationControllerTest {
  @Mock
  private EducationRepository educationRepository;

  @Mock
  private UserRepository userRepository;


  @InjectMocks
  private EducationController educationController;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp(){
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(educationController).build();
  }



  @Test
  public void testGetEducationForLoggedUser() throws Exception {
//    // Create a mock User and Education list
//    User user = new User();
//    user.setUsername("testUser");
//
//
//    Education education1 = new Education("COEP" ,  "Sample Desc 1"  ,  new Date() , new Date(), user);
//    Education education2 = new Education("VIIT" ,  "Sample Desc 2"  ,  new Date()  ,  new Date( ), user);
//    List<Education> educationList = new ArrayList<>();
//    educationList.add(education1);
//    educationList.add(education2);
//
//    // Mock the authentication and user retrieval
//    Authentication authentication = mock(Authentication.class);
//    when(authentication.getName()).thenReturn("testUser");
//    SecurityContext securityContext = mock(SecurityContext.class);
//    SecurityContextHolder.setContext(securityContext);
//    when(securityContext.getAuthentication()).thenReturn(authentication);
//    when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
//
//    // Mock the repository method
//    when(educationRepository.findByUser(user)).thenReturn(educationList);
//
//    // Perform the GET request
//    mockMvc.perform(MockMvcRequestBuilders.get("/api/educations"))
//      .andExpect(status().isOk())
//      .andExpect(MockMvcResultMatchers.jsonPath("$[0].instituteName").value("COEP"))
//      .andExpect(MockMvcResultMatchers.jsonPath("$[1].instituteName").value("VIIT"));
  }
}
