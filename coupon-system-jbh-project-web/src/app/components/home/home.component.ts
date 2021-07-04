import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  public showLogin = true;
  public userType: any;

  constructor(private title: Title, private loginService: LoginService) {}

  ngOnInit(): void {
    this.title.setTitle('Coupon System');
    if (sessionStorage.getItem('token') != null) {
      this.showLogin = false;
      this.userType = sessionStorage.getItem('clientType');
    }
  }

  logout(): void {
    this.loginService.logout();
  }
}
