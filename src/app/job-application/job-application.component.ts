import { Component } from '@angular/core';
import { Job, JobService } from '../job-list/job.service';
import { ActivatedRoute, Router } from '@angular/router';
import { JobApplication } from '../Types';

@Component({
  selector: 'app-job-application',
  templateUrl: './job-application.component.html',
  styleUrls: ['./job-application.component.css']
})
export class JobApplicationComponent {
  job!: Job;
  jobApplication !: JobApplication
  constructor(private jobService : JobService , private route: ActivatedRoute,  private router : Router,){
  }


  ngOnInit(){
    const token = localStorage.getItem("authToken")
    const jobId = this.route.snapshot.paramMap.get('id'); // Get job ID from route
    if(!jobId || !token) return
    this.jobService.getJobById(+jobId , token).subscribe((data) => {
      this.job = data;
    });
  }

  onApplyJob(){

    const token = localStorage.getItem("authToken")
    const jobId = this.route.snapshot.paramMap.get('id'); // Get job ID from route
    if(!jobId || !token) return

    this.jobService.applyForJob(token , +jobId).subscribe((data)=>{
        if(data){
            this.router.navigate(['dashboard'])
        }
    })
  }

  

}
