import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public user = new User();

  public constructor(
    private title: Title,
    private loginService: LoginService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Login');
  }

  public login(): void {
    this.loginService.login(this.user).subscribe(
      (resp: any) => {
        this.user.token = resp.token;

        // tslint:disable-next-line: no-non-null-assertion
        sessionStorage.setItem('email', this.user.email!);
        // tslint:disable-next-line: no-non-null-assertion
        sessionStorage.setItem('clientType', this.user.clientType!);
        // tslint:disable-next-line: no-non-null-assertion
        sessionStorage.setItem('token', this.user.token!);

        this.router.navigate([`/${this.user.clientType}`]);
      },

      (err: any) => {
        alert('Incorrect email or password. Please try again.');
        window.location.reload();
      }
    );
  }

  public logout(): void {
    this.loginService.logout();
  }
}
