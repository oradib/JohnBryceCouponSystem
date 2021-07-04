import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-manu',
  templateUrl: './manu.component.html',
  styleUrls: ['./manu.component.css'],
})
export class ManuComponent implements OnInit {
  isLogin$: Observable<boolean>;
  clientType$: Observable<string>;

  constructor(private loginService: LoginService) {
    this.isLogin$ = this.loginService.isLogin$.asObservable();
    this.clientType$ = this.loginService.clientType$.asObservable();
  }

  ngOnInit(): void {}

  logout(): void {
    this.loginService.logout();
  }
}
