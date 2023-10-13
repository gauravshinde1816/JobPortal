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
  userDetails: UserDetails = { jwtToken: '', user: { password: '', username: "", role: ""  , bio : "" , current_company : ""} }
  role : string  = ''
  isLoggedIn: boolean = false
  isLoading : boolean = true

  constructor(private centralDataService: CentralDataServiceService, private route: Router) {
  }

  ngOnInit() {
    const token: string | null = this.centralDataService.getTokenFromLocalStorage()
    if (token) {
       this.centralDataService.getLoggedInUserDetails(token).subscribe(
         (data : any)=>{
            // this.centralDataService.setUserDetails( { jwtToken : token ,  user : {password : data?.password , username : data?.username ,  role : data?.role}});
            this.centralDataService.userDetailsStateSubject.next({ jwtToken : token ,  user : {password : data?.password , username : data?.username ,  role : data?.role ,bio : data?.bio , current_company : data?.current_company}})
            this.userDetails = this.centralDataService.getUserDetails()
            this.centralDataService.loginStateSubject.next({role : data.role ,isLoggedIn : true});
            this.centralDataService.loadingStateSubject.next(false);
            this.route.navigate(["/dashboard"])
          }
       );   
    }
    else {
      // redirect to login / sign up page
      this.route.navigate([""])
    }
  }

  Logout() {
    this.isLoggedIn = false
    localStorage.removeItem("authToken")
    this.route.navigate([""])
  }
}
