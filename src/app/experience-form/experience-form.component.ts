import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ExperienceService } from '../experience-card/experience.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-experience-form',
  templateUrl: './experience-form.component.html',
  styleUrls: ['./experience-form.component.css']
})
export class ExperienceFormComponent {
  experienceForm: FormGroup;

  constructor(private fb: FormBuilder , private experienceService : ExperienceService , private router : Router) {
    this.experienceForm = this.fb.group({
      companyName: ['', Validators.required],
      description: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });
  }


  submitForm(){
       console.log(this.experienceForm.value)
       const token = localStorage.getItem("authToken")
       if(!token) return;

       this.experienceService.addExperience(token , this.experienceForm.value).subscribe((data:any)=>{
        console.log(data)   
        this.router.navigate(['dashboard'])
       })
  }
}
