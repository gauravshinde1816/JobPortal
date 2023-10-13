import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { JobFormComponent } from './job-form/job-form.component';
import { JobUpdateComponent } from './job-update/job-update.component';
import { ProfileComponent } from './profile/profile.component';
import { JobApplicationComponent } from './job-application/job-application.component';
import { JobApplicationListComponent } from './job-application-list/job-application-list.component';
import { ExperienceFormComponent } from './experience-form/experience-form.component';
import { EducationFormComponent } from './education-form/education-form.component';
import { JobNotificationsComponent } from './job-notifications/job-notifications.component';

const routes: Routes = [
  {
    path: "", component: HomeComponent
  },
  {
    path: "login", component: LoginComponent
  },
  {
    path: "signup", component: SignupComponent
  },
  {
    path: "dashboard", component: DashboardComponent
  },
  {
    path: "create-new-job", component: JobFormComponent
  },
  { path: 'update-job/:id', component: JobUpdateComponent },

  { path: 'apply-job/:id', component: JobApplicationComponent },

  {
    path: 'profile', component: ProfileComponent
  },
  { path: 'job-applications/:id', component: JobApplicationListComponent },

  {
    path : 'add-experience' , component : ExperienceFormComponent
  },
  {
    path : 'add-education' , component : EducationFormComponent
  },
  // {
  //   path : 'create-job-notification' , component : JobNotificationsComponent
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
