import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {


  username: string = '';
  password: string = '';
  role : string = ''
  email: string = '';

  constructor(private route :ActivatedRoute) {
  }

  ngOnInit(){
    this.route.queryParams.subscribe(params => {
        this.role  = params['role']
    })
  }

  onSubmit() {
    console.log(this.username ,  this.password  , this.role , this.password)
  }

}
