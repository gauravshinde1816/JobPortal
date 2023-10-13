import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JobService } from '../job-list/job.service';
import { CentralDataServiceService } from '../central-data-service.service';

@Component({
  selector: 'app-job-form',
  templateUrl: './job-form.component.html',
  styleUrls: ['./job-form.component.css']
})
export class JobFormComponent {
  job_title: string = "";
  job_description: string = "";
  job_category: string = "";
  company_details: string = "";



  constructor(private route : Router , private jobservice : JobService , private centralDataService : CentralDataServiceService){
  }

  onSubmit() {
    // Handle form submission here
    console.log('Form submitted:', this.job_title, this.job_description, this.job_category, this.company_details);

    const token : string | null = localStorage.getItem("authToken")
    if(token){
      this.jobservice.createNewJob(this.job_title , this.job_category , this.job_description , this.company_details , token).subscribe((data)=>{
        console.log(data)
        // this.sendJobNotification(this.job_category);
        this.route.navigate(["dashboard"])
      })
    }
    else{
      this.route.navigate([""])
    }
    
  }


  sendJobNotification(jobCategory : string) {
    const token = localStorage.getItem("authToken")
    if(!token) return;
    this.centralDataService.sendJobNotification('New job in ' + jobCategory , token).subscribe(() => {
      console.log('Notification sent successfully');
    });
  }
}
