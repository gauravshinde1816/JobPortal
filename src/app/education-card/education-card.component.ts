import { Component, Input } from '@angular/core';
import { EducationService } from './education.service';
import { User, UserDetails } from '../Types';
import { CentralDataServiceService } from '../central-data-service.service';



@Component({
  selector: 'app-education-card',
  templateUrl: './education-card.component.html',
  styleUrls: ['./education-card.component.css']
})



export class EducationCardComponent {
  userDetails!: UserDetails
  @Input() education!: {
    id : number;
    instituteName: string;
    description: string;
    startDate: number;
    endDate: number;
    user : User
  };

  constructor(private educationService : EducationService , private centralDataService : CentralDataServiceService){
  }


  ngOnInit(){
    this.centralDataService.userDetailsStateSubject.subscribe((val)=>{
      this.userDetails = val
    })
  }

  onDeleteEducation( id : number){
    const token = localStorage.getItem("authToken")
    if(!token) return;
    this.educationService.deleteEducation(token ,  id).subscribe((data:any)=>{
      window.location.reload()
    })
  }
}
