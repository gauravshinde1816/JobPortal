import { Component, Input } from '@angular/core';
import { Location } from '@angular/common';
import { Job, JobService } from '../job-list/job.service';
import { Router } from '@angular/router';
import { JobApplication } from '../Types';

@Component({
  selector: 'app-job-item',
  templateUrl: './job-item.component.html',
  styleUrls: ['./job-item.component.css']
})
export class JobItemComponent {
  @Input()
  job!: Job;

  @Input() role: string = ''
  @Input() jobStatus !: boolean | undefined
  @Input() jobDetails !: boolean | undefined
  @Input() jobApprovalPage  !: boolean | undefined
  applied: boolean = false

  constructor(private jobService: JobService, private route: Router, private location: Location) {
  }


  ngOnInit() {
    const token = localStorage.getItem("authToken")
    if (!token) return;
    this.jobService.getJobApplicationsForJobByLoggedInUser(token, this.job.job_id).subscribe((data: JobApplication) => {
      if (data) {
        this.applied = true
        this.jobStatus = data.status
      }
      else {
        this.applied = false
      }
    })
  }

  withDrawJobApplication() {
    const token = localStorage.getItem("authToken")
    if (!token) return
    this.jobService.getJobApplicationsForJobByLoggedInUser(token, this.job.job_id).subscribe((data: JobApplication) => {
      this.jobService.withDrawJobApplication(token, data.id).subscribe(
        (data: any) => {
          this.route.navigate(['dashboard'])
        }
      )
    })
  }


  onDeleteJob(id: Number) {
    const token = localStorage.getItem("authToken")
    if (token) {
      this.jobService.deleteJob(id, token).subscribe(() => {
        window.location.reload()
      })
    }
  }
}
