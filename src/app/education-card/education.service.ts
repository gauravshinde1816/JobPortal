
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs"
import { User } from '../Types';

const baseUrl = 'http://localhost:8080';

export interface Education {
  id : number;
  instituteName: string;
  description: string;
  startDate: number;
  endDate: number;
  user :User
}


@Injectable({
  providedIn: 'root'
})


export class EducationService {

  constructor(private http: HttpClient) { }



  getEducationForLoggedInUser(token: string): Observable<any> {
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')

    return this.http.get(`${baseUrl}/api/educations` ,  {headers})
  }

  getEducationForUser(token: string , user_id : number): Observable<any> {
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.get(`${baseUrl}/api/educations/${user_id} `,  {headers})
  }


  addEducation(token : string , education: Education): Observable<any>{
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.post(`${baseUrl}/api/educations` , education , {headers})
  }

  deleteEducation(token : string , education_id: number): Observable<void>{
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.delete<void>(`${baseUrl}/api/educations/${education_id}` , {headers})
  }


}
