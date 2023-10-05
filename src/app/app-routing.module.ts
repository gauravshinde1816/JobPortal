import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { JobFormComponent } from './job-form/job-form.component';

const routes: Routes = [
  {
    path : "" ,  component : HomeComponent
  },
  {
    path : "login" , component : LoginComponent
  },
  {
    path : "signup" , component : SignupComponent
  },
  {
    path : "dashboard" , component : DashboardComponent
  },
  {
    path : "create-new-job" , component : JobFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
