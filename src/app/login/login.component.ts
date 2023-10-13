import { Component } from '@angular/core';
import {LoginService} from "./login.service"
import { loginResponse } from './loginTypes';
import { Router } from '@angular/router';
import { CentralDataServiceService } from '../central-data-service.service';
import { UserDetails } from '../Types';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  jwtResponse : any = {};

  constructor(private loginService : LoginService ,  private router : Router , private centralDataService : CentralDataServiceService ){}

  onSubmit() {
    this.loginService.postData({username : this.username , password : this.password }).subscribe(
      (data : UserDetails )=> {

        if(data.jwtToken !== null && data.user !== null){
          this.loginService.setAuthToken(data.jwtToken)
          this.username = '';
          this.password = '';
          this.centralDataService.userDetailsStateSubject.next(data)
          this.centralDataService.loginStateSubject.next({role : data.user.role ,isLoggedIn : true});
          this.centralDataService.loadingStateSubject.next(false)
          this.router.navigate(['dashboard'])
        }
        else {
            // set Alerts
        }
        
      }
      
    )
  }
}

