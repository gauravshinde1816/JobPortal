import { Injectable } from '@angular/core';
import { UserDetails } from './Types';

@Injectable({
  providedIn: 'root'
})
export class CentralDataServiceService {

  private userDetails : UserDetails = {jwtToken  : '' , user : {password : '' , username : "" , role : ""}}
 isLoggedIn : boolean = false
  constructor() { }


  setUserDetails(userDetails : UserDetails , isLoggedIn : boolean){
    this.userDetails = userDetails
    this.isLoggedIn = isLoggedIn
  }


  getUserDetails(){
    return this.userDetails;
  }



  clear(){
    this.userDetails = {jwtToken : "" , user : {password : "" , role : "" ,username : '' }}
  }


}
