import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  role = localStorage.getItem('role');
  constructor(
    private auth:AuthService
  ) { }

  ngOnInit(): void {
  }

  loggedin():boolean{
    return this.auth.checkLogin();
  }

}
