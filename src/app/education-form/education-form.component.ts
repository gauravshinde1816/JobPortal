import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EducationService } from '../education-card/education.service';

@Component({
  selector: 'app-education-form',
  templateUrl: './education-form.component.html',
  styleUrls: ['./education-form.component.css']
})
export class EducationFormComponent {

  experienceForm: FormGroup;

  constructor(private fb: FormBuilder , private educationService : EducationService , private router : Router) {
    this.experienceForm = this.fb.group({
      instituteName: ['', Validators.required],
      description: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });
  }


  submitForm(){
       console.log(this.experienceForm.value)
       const token = localStorage.getItem("authToken")
       if(!token) return;

       this.educationService.addEducation(token , this.experienceForm.value).subscribe((data:any)=>{
        console.log(data)   
        this.router.navigate(['dashboard'])
       })
  }

}
