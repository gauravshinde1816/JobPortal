import { Component , Input } from '@angular/core';
import {  Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  @Input() role : string = ''
  @Input() isLoggedIn : boolean = false
  @Input() LogoutFunction : Function = ()=>{}

  constructor(private route : Router){
  }

}
