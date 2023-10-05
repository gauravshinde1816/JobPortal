export interface UserDetails   {
    user :User,
    jwtToken : string
}



export interface User {
    username :string, 
    password : string, 
    role : string,
}