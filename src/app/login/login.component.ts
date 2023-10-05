import { Component } from '@angular/core';
import {LoginService} from "./login.service"
import { loginResponse } from './loginTypes';
import { Router } from '@angular/router';
import { CentralDataServiceService } from '../central-data-service.service';

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
      (data : loginResponse )=> {

        if(data.jwtToken !== null && data.user !== null){
          this.loginService.setAuthToken(data.jwtToken)
          this.centralDataService.setUserDetails(data , true);
          this.username = ''
          this.password = ''
          this.router.navigate(['dashboard'])
        }
        else {
            // set Alerts
        }
        
      }
      
    )
  }
}

