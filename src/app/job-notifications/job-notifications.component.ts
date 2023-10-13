import { Component } from '@angular/core';
import { CentralDataServiceService } from '../central-data-service.service';


declare const AWS: any; // Declare AWS as a global variable


@Component({
  selector: 'app-job-notifications',
  templateUrl: './job-notifications.component.html',
  styleUrls: ['./job-notifications.component.css']
})
export class JobNotificationsComponent {
  jobCategory: string = '';
  notifications: string[] = [];
  snsClient: any; // AWS SNS client
  subscription: any; // AWS SNS subscription object

  constructor(private centralDataService: CentralDataServiceService) { }

  ngOnInit(): void {
    this.initializeSNSClient();
  }

  initializeSNSClient() {
    AWS.config.region = 'ap-south-1'; // Set your AWS region

    this.snsClient = new AWS.SNS({
      apiVersion: '2010-03-31',
      accessKeyId: 'AKIAY7OMEHNKOSFUINPJ',
      secretAccessKey: 'R+pRR1y4JvzOtvzm50sY+p7j15u7eVr24zQO2qEz',
    });

    // Subscribe to the SNS topic for the selected job category
    this.subscribeToNotifications();
  }

  subscribeToNotifications() {
    const params = {
      Protocol: 'sms', 
      TopicArn: 'arn:aws:sns:ap-south-1:617292774228:JobNotification',
      Endpoint: '8788504562',
    };

    this.snsClient.subscribe(params, (err : any, data : any) => {
      if (err) {
        console.log('Error subscribing to SNS:', err);
      } else {
        console.log('Subscribed to SNS:', data);
        this.subscription = data;
      }
    });
  }

  ngOnDestroy() {
    // Unsubscribe from SNS topic when the component is destroyed
    if (this.subscription) {
      const params = {
        SubscriptionArn: this.subscription.SubscriptionArn
      };
      this.snsClient.unsubscribe(params, (err : any, data : any) => {
        if (err) {
          console.log('Error unsubscribing from SNS:', err);
        } else {
          console.log('Unsubscribed from SNS:', data);
        }
      });
    }
  }

  sendJobNotification() {
    const token = localStorage.getItem("authToken")
    if(!token) return;
    this.centralDataService.sendJobNotification('New job in ' + this.jobCategory , token).subscribe(() => {
      console.log('Notification sent successfully');
    });
  }
}
