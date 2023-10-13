import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Job, JobRequest, JobService } from '../job-list/job.service';

@Component({
  selector: 'app-job-update',
  templateUrl: './job-update.component.html',
  styleUrls: ['./job-update.component.css']
})
export class JobUpdateComponent implements OnInit {
  job!: Job;
  jobTitle: string = "";
  jobDescription: string = "";
  jobCategory: string = "";
  companyDetails: string = "";

  constructor(
    private route: ActivatedRoute,
    private router : Router,
    private jobService: JobService
  ) {}

  ngOnInit() {

    const token = localStorage.getItem("authToken")
    const jobId = this.route.snapshot.paramMap.get('id'); // Get job ID from route
    if(!jobId || !token) return
    this.jobService.getJobById(+jobId , token).subscribe((data) => {
      this.job = data;
      this.jobTitle = this.job.job_title;
      this.jobDescription = this.job.job_description;
      this.jobCategory = this.job.job_category;
      this.companyDetails = this.job.company_details;
    });
  }

  updateJob() {
    // Create an updated job object with the form values

    const token = localStorage.getItem("authToken")

    if(!token) return
    const updatedJob: JobRequest = {
      job_title: this.jobTitle,
      job_description: this.jobDescription,
      job_category: this.jobCategory,
      company_details: this.companyDetails,
      // Add other properties as needed
    };

    // Call the API to update the job
    this.jobService.updateJob(this.job.job_id, updatedJob , token).subscribe((data) => {
      // Handle success or show a confirmation message
      this.router.navigate(['dashboard'])
      console.log('Job updated successfully');
    });
  }
}
