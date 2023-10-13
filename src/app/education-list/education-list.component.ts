import { Component  , Input} from '@angular/core';
import { Education, EducationService } from '../education-card/education.service';
import { UserDetails } from '../Types';
import { CentralDataServiceService } from '../central-data-service.service';

@Component({
  selector: 'app-education-list',
  templateUrl: './education-list.component.html',
  styleUrls: ['./education-list.component.css']
})
export class EducationListComponent {
  userDetails! : UserDetails
  panelOpenState = true;
  educationList : Education[] = []
  @Input() applicantID! : number

 

  constructor(private educationService :EducationService , private centralDataService :CentralDataServiceService){
  }

  ngOnInit(){
    
   
      this.centralDataService.userDetailsStateSubject.subscribe((val)=>{
        this.userDetails = val
      })

    const token = localStorage.getItem("authToken")
    if(token && !this.applicantID){
      this.educationService.getEducationForLoggedInUser(token).subscribe((data)=>{
        this.educationList = data
      });
    }

    if(token && this.applicantID){
      this.educationService.getEducationForUser(token , this.applicantID).subscribe((data)=>{
        this.educationList = data
      });
    }

    

    
  }

}
