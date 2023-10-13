import { Component , Input } from '@angular/core';
import { Experience, ExperienceService } from '../experience-card/experience.service';
import { CentralDataServiceService } from '../central-data-service.service';
import { UserDetails } from '../Types';

@Component({
  selector: 'app-experience-list',
  templateUrl: './experience-list.component.html',
  styleUrls: ['./experience-list.component.css']
})
export class ExperienceListComponent {

  panelOpenState = false
  userDetails !: UserDetails | any
  experienceList : Experience[] = []
  @Input() applicantID !: number

  constructor(private experienceService :ExperienceService , private centralDataService : CentralDataServiceService){
  }


  ngOnInit(){

    this.centralDataService.userDetailsStateSubject.subscribe((val)=>{
      this.userDetails = val
    })
    
    const token = localStorage.getItem("authToken")
    if(token && !this.applicantID){
      this.experienceService.getExperienceForLoggedInUser(token).subscribe((data : any)=>{
        this.experienceList = data
      
      })
    }
    
    
    
    
    if(token && this.applicantID){
      this.experienceService.getExperienceForUser(token , this.applicantID).subscribe((data : any)=>{
        this.experienceList = data
      })
    }  

    
  }

}
