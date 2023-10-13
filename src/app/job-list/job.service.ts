import { Injectable } from '@angular/core';
import { User } from '../Types';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CentralDataServiceService } from '../central-data-service.service';
import { Observable } from 'rxjs';



const baseUrl = 'http://localhost:8080';


export interface Job {
  job_id : number;
  job_title: string;
  job_description: string;
  job_category: string;
  company_details: string;
  hiring_manager: User; // Assuming you have a User model
}


export interface JobRequest {
  job_title: string;
  job_description: string;
  job_category: string;
  company_details: string;
}

@Injectable({
  providedIn: 'root'
})

export class JobService {


  constructor(private http: HttpClient) { }

  getAllJobsPostedByHiringManager(token: string): Observable<any> {
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.get(`${baseUrl}/api/jobs/hiring/me`, { headers })
  }

  getAllJobs(token: string): Observable<any> {
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.get(`${baseUrl}/api/jobs`, { headers })
  }

  createNewJob(job_title: string, job_category: string, job_description: string, company_details: string, token: string): Observable<any> {
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
    return this.http.post(`${baseUrl}/api/jobs`,  { job_title, job_category, job_description, company_details }, {headers} )
  }

  deleteJob(id : Number ,  token : string) : Observable<void> {
    const headers = new HttpHeaders()
      .set('Authorization', token)
      .set('Access-Control-Allow-Origin', '*')
      return this.http.delete<void>(`${baseUrl}/api/jobs/${id}`, {headers})
  }


  getJobById(jobId: number , token : string): Observable<Job> {
    const headers = new HttpHeaders()
    .set('Authorization', token)
    .set('Access-Control-Allow-Origin', '*')
    return this.http.get<Job>(`${baseUrl}/api/jobs/${jobId}` ,  {headers});
  }

  updateJob(jobId: number, jobData: JobRequest ,  token : string): Observable<Job> {
    const headers = new HttpHeaders()
    .set('Authorization', token)
    .set('Access-Control-Allow-Origin', '*')
    return this.http.put<Job>(`${baseUrl}/api/jobs/${jobId}`, jobData ,  {headers});
  }


  getJobApplicationsForLoggedInUser(token : string) : Observable<any>{
    const headers = new HttpHeaders()
    .set('Authorization', token)
    .set('Access-Control-Allow-Origin', '*')
    return this.http.get(`${baseUrl}/api/applications/me` ,  {headers});
  }


  getJobApplicationsForJobByLoggedInUser(token : string  ,  job_id : number)  : Observable<any>{
    const headers = new HttpHeaders()
    .set('Authorization', token)
    .set('Access-Control-Allow-Origin', '*')
    return this.http.get(`${baseUrl}/api/applications/${job_id}/applied/me` ,  {headers});
  }


  applyForJob(token : string  ,  job_id : number) :Observable<any> {
    const headers = new HttpHeaders()
    .set('Authorization', token)
    .set('Access-Control-Allow-Origin', '*')
    return this.http.post(`${baseUrl}/api/applications/${job_id}/apply` , {} , {headers});
  }

  withDrawJobApplication(token : string  ,  application_id : number) :Observable<any> {
    const headers = new HttpHeaders()
    .set('Authorization', token)
    .set('Access-Control-Allow-Origin', '*')
    return this.http.delete(`${baseUrl}/api/applications/${application_id}` ,  {headers});
  }


  getJobApplicationsForJob(token : string  ,  job_id : number)  : Observable<any>{
    const headers = new HttpHeaders()
    .set('Authorization', token)
    .set('Access-Control-Allow-Origin', '*')
    return this.http.get(`${baseUrl}/api/applications/${job_id}/list` ,  {headers});
  }

  changeStatusOfJobApplication(token : string  ,  application_id : number) :Observable<any> {
    const headers = new HttpHeaders()
    .set('Authorization', token)
    .set('Access-Control-Allow-Origin', '*')
    return this.http.put(`${baseUrl}/api/applications/${application_id}/status` , {}, {headers});
  }


  


}
