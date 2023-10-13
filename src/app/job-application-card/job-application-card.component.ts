import { Component, Input } from '@angular/core';
import { Education, EducationService } from '../education-card/education.service';
import { JobApplication } from '../Types';
import { Experience, ExperienceService } from '../experience-card/experience.service';
import { JobService } from '../job-list/job.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-job-application-card',
  templateUrl: './job-application-card.component.html',
  styleUrls: ['./job-application-card.component.css']
})
export class JobApplicationCardComponent {


  @Input() jobApplication!: JobApplication
  educationList:Education[] = []
  experienceList:Experience[] = []


  constructor(private jobService : JobService ,  private educationService : EducationService , private experienceService : ExperienceService , private router : Router){}



  ngOnInit(){
    const token = localStorage.getItem("authToken")

    if(!token) return

    this.educationService.getEducationForUser(token ,  this.jobApplication.applicant.id).subscribe((data : any)=>{
      this.educationList = data
    })

    this.experienceService.getExperienceForUser(token ,  this.jobApplication.applicant.id).subscribe((data : any)=>{
      this.experienceList = data
    })
  }


  onRejectApplication(){
     const token = localStorage.getItem("authToken")
     if(!token) return
     this.jobService.changeStatusOfJobApplication(token , this.jobApplication.id).subscribe((data:any)=>{
        this.router.navigate(['dashboard'])
     })
  }



}
