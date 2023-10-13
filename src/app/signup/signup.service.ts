import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface SignUpRequest {
  username : string
  password: string
  bio: string,
  current_company :string
  role: string
  email:string
}

@Injectable({
  providedIn: 'root'
})
export class SignupService {
  private  baseURL : string = "http://localhost:8080"
  constructor(private http :HttpClient) { }


  register(signup : SignUpRequest) : Observable<any>{
    return this.http.post(`${this.baseURL}/api/auth/signup` , signup);
  }
  setAuthToken(token : string) : void {
    localStorage.setItem("authToken" ,  token);
 }
}
