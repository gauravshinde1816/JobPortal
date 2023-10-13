import { Job } from "./job-list/job.service"

export interface UserDetails   {
    user :User,
    jwtToken : string
}



export interface User {
    username :string, 
    password : string, 
    role : string,
    bio : string , 
    current_company : string
}

export interface UserApplicant {
    id : number , 
    username :string, 
    email : string , 
    password : string, 
    role : string,
}


export interface JobApplication {
    id : number
    job : Job,
    applicant : UserApplicant,
    status : boolean
}


export interface loginEvent {

    role : string
    isLoggedIn : boolean

}