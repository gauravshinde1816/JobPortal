import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  role : string = ''

  constructor(private route :ActivatedRoute) {
  }

  ngOnInit(){
    this.route.queryParams.subscribe(params => {
        this.role  = params['role']
    })
  }

  onSubmit() {
    console.log(this.username ,  this.password  , this.role)
  }
}

