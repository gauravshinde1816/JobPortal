import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = 'http://localhost:8080'; // Replace with your Spring backend URL

  constructor(private http: HttpClient) {}

  // Example POST request
  postData(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/api/auth/login`, data);
  }

  setAuthToken(token : string) : void {
     localStorage.setItem("authToken" ,  token);
  }
}
