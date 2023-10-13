package com.ideas.jobportal.controller;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.ideas.jobportal.models.JobNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notifications")
public class JobNotificationController {
  @Value("${sns.topic.arn}")
  private String topicArn;

  private final AmazonSNS amazonSNS;

  @Autowired
  public JobNotificationController(AmazonSNS amazonSNS) {
    this.amazonSNS = amazonSNS;
  }

  @PostMapping("/send")
  public void sendJobNotification(@RequestBody JobNotification jobNotification) {
    PublishRequest request = new PublishRequest(topicArn, jobNotification.getMessage());
    amazonSNS.publish(request);
  }
}
