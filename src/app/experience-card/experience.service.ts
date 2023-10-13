import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../Types';

const baseUrl = 'http://localhost:8080';

export interface Experience{
  id : number,
  companyName: string;
  description: string;
  startDate: number;
  endDate: number;
  user : User
}

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {

  constructor(private http : HttpClient) { }

  
  getExperienceForLoggedInUser(token : string){
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.get(`${baseUrl}/api/experiences` , {headers})
  }


  getExperienceForUser(token : string , user_id : number){
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.get(`${baseUrl}/api/experiences/${user_id}` , {headers})
  }


  addExperience(token : string , experience: Experience): Observable<any>{
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.post(`${baseUrl}/api/experiences` , experience , {headers})
  }

  deleteExperience(token : string , experience_id: number): Observable<void>{
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.delete<void>(`${baseUrl}/api/experiences/${experience_id}` , {headers})
  }
}
