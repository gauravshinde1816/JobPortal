import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms'; // Import this for [(ngModel)] to work
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavbarComponent } from './navbar/navbar.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { JobFormComponent } from './job-form/job-form.component';
import { JobListComponent } from './job-list/job-list.component';
import { LoaderComponent } from './loader/loader.component';
import { JobItemComponent } from './job-item/job-item.component';
import { ProfileComponent } from './profile/profile.component';
import { MatIconModule } from '@angular/material/icon';
import {MatChipsModule} from '@angular/material/chips';
import { MatBadgeModule } from '@angular/material/badge';
import {MatExpansionModule} from '@angular/material/expansion';
import { ReactiveFormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { JobUpdateComponent } from './job-update/job-update.component';
import { EducationListComponent } from './education-list/education-list.component';
import { EducationCardComponent } from './education-card/education-card.component';
import { ExperienceListComponent } from './experience-list/experience-list.component';
import { ExperienceCardComponent } from './experience-card/experience-card.component';
import { JobApplicationComponent } from './job-application/job-application.component';
import { JobApplicationListComponent } from './job-application-list/job-application-list.component';
import { JobApplicationCardComponent } from './job-application-card/job-application-card.component';
import { ExperienceFormComponent } from './experience-form/experience-form.component';
import { MatNativeDateModule } from '@angular/material/core';
import { EducationFormComponent } from './education-form/education-form.component';
import { JobNotificationsComponent } from './job-notifications/job-notifications.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    SignupComponent,
    DashboardComponent,
    NavbarComponent,
    JobFormComponent,
    JobListComponent,
    LoaderComponent,
    JobItemComponent,
    ProfileComponent,
    JobUpdateComponent,
    EducationListComponent,
    EducationCardComponent,
    ExperienceListComponent,
    ExperienceCardComponent,
    JobApplicationComponent,
    JobApplicationListComponent,
    JobApplicationCardComponent,
    ExperienceFormComponent,
    EducationFormComponent,
    JobNotificationsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    HttpClientModule,
    MatToolbarModule,
    MatListModule,
    MatGridListModule,
    MatChipsModule,
    MatIconModule,
    MatExpansionModule,
    MatBadgeModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
