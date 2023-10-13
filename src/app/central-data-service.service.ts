import { Injectable } from '@angular/core';
import { User, UserDetails, loginEvent } from './Types';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { loginResponse } from './login/loginTypes';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CentralDataServiceService {

  userDetails: UserDetails = { jwtToken: '', user: { password: '', username: "", role: "" , bio : "" ,  current_company : "" } }
  public userDetailsStateSubject = new BehaviorSubject<UserDetails>({ jwtToken: '', user: { password: '', username: "", role: "" , bio : "" , current_company : "" } })
  isLoggedIn: boolean = false
  public loginStateSubject = new BehaviorSubject<loginEvent>({role : ' ' , isLoggedIn : false});
  public loginStateSubscriber = this.loginStateSubject.asObservable();
  isLoading: boolean = true
  public loadingStateSubject = new BehaviorSubject<boolean>(true);
  public loadingStateSubscriber = this.loadingStateSubject.asObservable();
  jobListCountForHiringManagerSubject = new BehaviorSubject<number>(0);

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8080';


  setUserDetails(userDetails: UserDetails) {
    this.userDetails = userDetails;
  }

  getUserDetails() {
    return this.userDetails;
  }

  clear() {
    this.userDetails = { jwtToken: "", user: { password: "", role: "", username: '' , bio : "" , current_company : "" } }
  }


  getLoggedInUserDetails(token: string): Observable<any> {

    const headers = new HttpHeaders()
      .set('Authorization', token)
    return this.http.get(`${this.baseUrl}/api/auth/me`, { headers })
  }


  getTokenFromLocalStorage() {
    return localStorage.getItem("authToken")
  }


  displayState() {
    console.log("UserDetails ", this.userDetails, "isLoading ", this.isLoading, "isLoggedIn ", this.isLoggedIn)
  }


  // notification service
  private apiUrl = '/api/notifications';


  sendJobNotification(message: string , token: string) {
    const headers = new HttpHeaders()
    .set('Authorization', token)
    return this.http.post(`${this.apiUrl}/send`, { message } ,  {headers});
  }


}
