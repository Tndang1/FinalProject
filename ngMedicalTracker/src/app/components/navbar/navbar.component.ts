import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    private auth:AuthService
  ) { }

  ngOnInit(): void {
  }

  loggedin():boolean{
    return this.auth.checkLogin();
  }

  checkRole():string{
    return localStorage.getItem('role');
  }

  refresh(): void {
    window.location.reload();
}

}
