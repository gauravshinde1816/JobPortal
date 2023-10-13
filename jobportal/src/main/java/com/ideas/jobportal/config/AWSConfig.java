package com.ideas.jobportal.config;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

  @Bean
  public AmazonSNS amazonSNS() {
    return AmazonSNSClientBuilder.standard().withRegion("ap-south-1").build();
  }
}
