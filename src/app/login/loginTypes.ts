export interface loginResponse   {
    user :User,
    jwtToken : string
}



interface User {
    username :string, 
    password : string, 
    role : string,
}