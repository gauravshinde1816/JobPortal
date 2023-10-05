import { Component } from '@angular/core';
import {ActivatedRoute, Router} from  "@angular/router"
import { CentralDataServiceService } from '../central-data-service.service';
import { UserDetails } from '../Types';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})




export class DashboardComponent {
   

  userDetails : UserDetails = {jwtToken  : '' , user : {password : '' , username : "" , role : ""}}
  role : string = ''
  

  constructor(private centralDataService : CentralDataServiceService, private route : Router){
  }

  ngOnInit(){
    this.userDetails =  this.centralDataService.getUserDetails()
  }

  Logout(){
    this.centralDataService.clear()
    localStorage.removeItem("authToken")
    this.route.navigate([""])
  }

}
