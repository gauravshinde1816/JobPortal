import { Component } from '@angular/core';
import { Job, JobService } from './job.service';
import { CentralDataServiceService } from '../central-data-service.service';
import { Router } from '@angular/router';
import { loginEvent } from '../Types';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css']
})
export class JobListComponent {


  // list of jobs
  JobList: Job[] = []
  loginState : loginEvent = {isLoggedIn : false ,  role : ''}


  constructor(private jobService: JobService, private centralDataService: CentralDataServiceService, private route: Router) {
  }


  ngOnInit() {

    // update the role first
    this.centralDataService.loginStateSubject.subscribe((val : loginEvent)=>{
      this.loginState.role = val.role
      this.loginState.isLoggedIn = val.isLoggedIn
    })
    const token: string | null = this.centralDataService.getTokenFromLocalStorage()

   

    if (token) {
      if(this.loginState.role === 'HM'){
        this.jobService.getAllJobsPostedByHiringManager(token).subscribe((data) => {
          this.JobList = data
          this.centralDataService.jobListCountForHiringManagerSubject.next(this.JobList.length)
        })
      }
      else if(this.loginState.role === 'JS'){
        this.jobService.getAllJobs(token).subscribe((data) => {
          this.JobList = data
          this.centralDataService.jobListCountForHiringManagerSubject.next(this.JobList.length)
        })
      }
    }
    else {
      this.route.navigate([''])
    }
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
