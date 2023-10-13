import { Component, Input } from '@angular/core';
import { ExperienceService } from './experience.service';
import { User, UserDetails } from '../Types';
import { CentralDataServiceService } from '../central-data-service.service';

@Component({
  selector: 'app-experience-card',
  templateUrl: './experience-card.component.html',
  styleUrls: ['./experience-card.component.css']
})
export class ExperienceCardComponent {

  userDetails!: UserDetails
  @Input() experience!: {
    id : number;
    companyName: string;
    description: string;
    startDate: number;
    endDate: number;
    user : User
  };


  constructor(private experienceService : ExperienceService , private centralDataService : CentralDataServiceService){

  }


  ngOnInit(){
    this.centralDataService.userDetailsStateSubject.subscribe((data)=>{
      this.userDetails = data
      console.log("EXP CARD USER : " , this.experience.user ,  " " , this.userDetails.user)
    })
    
  }

  onDeleteExp(id : number){
    const token = localStorage.getItem("authToken")
    if(!token) return;
    this.experienceService.deleteExperience(token ,  id).subscribe((data:any)=>{
      console.log("Exp deleted")
      window.location.reload()
    })
  }
}
