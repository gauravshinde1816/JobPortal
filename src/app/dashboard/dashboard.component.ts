import { Component, EventEmitter, Output } from '@angular/core';
import {ActivatedRoute, Router} from  "@angular/router"
import { CentralDataServiceService } from '../central-data-service.service';
import { UserDetails } from '../Types';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})




export class DashboardComponent {
   

  userDetails : UserDetails = {jwtToken  : '' , user : {password : '' , username : "" , role : "" , bio : "" , current_company : ""}}
  role : string = ''
  isLoading  : boolean = true
  

  constructor(private centralDataService : CentralDataServiceService, private route : Router){
  }

  ngOnInit(){
    this.centralDataService.loadingStateSubscriber.subscribe((val: boolean) => {
      this.isLoading = val;
    })
    
    this.centralDataService.userDetailsStateSubject.subscribe((val : UserDetails) =>{
      this.userDetails = val
    })
  }

  Logout(){
    this.centralDataService.clear()
    localStorage.removeItem("authToken")
    this.route.navigate([""])
  }


  stopParentScroll() {
    // Disable scrolling
    document.body.style.overflow = 'hidden';
  }
  
  allowParentScroll() {
    // Allow scrolling
    document.body.style.overflow = 'auto';
  }
  
}
