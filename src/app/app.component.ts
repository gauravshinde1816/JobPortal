import { Component } from '@angular/core';
import { UserDetails } from './Types';
import { CentralDataServiceService } from './central-data-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  userDetails : UserDetails = {jwtToken  : '' , user : {password : '' , username : "" , role : ""}}
  role : string = ''
  isLoggedIn : boolean = false
  

  constructor(private centralDataService : CentralDataServiceService, private route : Router){
  }

  ngOnInit(){
    this.userDetails =  this.centralDataService.getUserDetails()
    this.isLoggedIn = this.centralDataService.isLoggedIn
  }

  Logout(){
    this.centralDataService.clear()
    this.isLoggedIn = false
    localStorage.removeItem("authToken")
    this.route.navigate([""])
  }
}
