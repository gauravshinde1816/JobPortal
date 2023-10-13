import { Component } from '@angular/core';
import { JobService } from '../job-list/job.service';
import { JobApplication } from '../Types';
import { ActivatedRoute, Router } from '@angular/router';
import { Education } from '../education-card/education.service';
import { Experience } from '../experience-card/experience.service';

@Component({
  selector: 'app-job-application-list',
  templateUrl: './job-application-list.component.html',
  styleUrls: ['./job-application-list.component.css']
})
export class JobApplicationListComponent {

  jobApplicationList: JobApplication[] = []


  educationList:Education[] = []
  experienceList:Experience[] = []

  
  constructor(private route: ActivatedRoute,
    private router: Router,
    private jobService: JobService) {
  }


  ngOnInit() {
    console.log(this.jobApplicationList.length)
    const token = localStorage.getItem("authToken")
    const jobId = this.route.snapshot.paramMap.get('id'); // Get job ID from route
    if (!jobId || !token) return
    this.jobService.getJobApplicationsForJob(token , +jobId).subscribe((data : any)=>{
        this.jobApplicationList = data
    })
  }
}
