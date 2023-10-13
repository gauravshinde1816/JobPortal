import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SignupService } from './signup.service';
import { CentralDataServiceService } from '../central-data-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {


  username: string = '';
  password: string = '';
  role : string = ''
  email: string = '';
  bio : string = ''
  current_company : string = ''

  constructor(private route :ActivatedRoute , private router : Router ,  private signUpService : SignupService , private centralDataService : CentralDataServiceService) {
  }

  ngOnInit(){
    this.route.queryParams.subscribe(params => {
        this.role  = params['role']
    })
  }

  onSubmit() {
    this.signUpService.register({username : this.username ,  password : this.password , role : this.role ,  current_company : this.current_company , bio : this.bio , email : this.email}).subscribe((data)=>{
      this.signUpService.setAuthToken(data.jwtToken)
          this.username = '';
          this.password = '';
          this.centralDataService.userDetailsStateSubject.next(data)
          this.centralDataService.loginStateSubject.next({role : data.user.role ,isLoggedIn : true});
          this.centralDataService.loadingStateSubject.next(false)
          this.router.navigate(['dashboard'])
    });
    // console.log(this.username ,  this.password  , this.role , this.password , this.bio ,  this.current_company)
  }

}
