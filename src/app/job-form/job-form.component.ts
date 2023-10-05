import { Component } from '@angular/core';

@Component({
  selector: 'app-job-form',
  templateUrl: './job-form.component.html',
  styleUrls: ['./job-form.component.css']
})
export class JobFormComponent {
  job_title: string = "";
  job_description: string = "";
  job_category: string = "";
  company_details: string = "";

  onSubmit() {
    // Handle form submission here
    console.log('Form submitted:', this.job_title, this.job_description, this.job_category, this.company_details);
  }
}
