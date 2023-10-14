import { Component , Input } from '@angular/core';
import {  Router } from '@angular/router';
import { CentralDataServiceService } from '../central-data-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

   role : string = ''
  isLoggedIn : boolean = false
  @Input() LogoutFunction : Function = ()=>{}



  constructor(private route : Router , private centralDataService : CentralDataServiceService){
  }

  ngOnInit() {
    this.centralDataService.loginStateSubscriber.subscribe(({role , isLoggedIn}) => {
      this.isLoggedIn = isLoggedIn;
      this.role = role
    })

    console.log(this.isLoggedIn ,  " "  , this.role)

  }


  navigateToHome(){

    if(this.isLoggedIn){
      this.route.navigate(['dashboard'])
    }
    else{
      this.route.navigate([''])
    }
  }

  // ngOnInit(){
  //    console.log("Navbar called with " , this.centralDataService.getUserDetails().user.role)
  //    console.log("IsLogged In " ,  this.centralDataService.isLoggedIn)
  //     this.role = this.centralDataService.getUserDetails().user.role
  //     this.isLoggedIn = this.centralDataService.isLoggedIn
  // }

}
