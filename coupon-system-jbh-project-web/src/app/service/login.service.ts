import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  isLogin$ = new BehaviorSubject<boolean>(!!sessionStorage.getItem('token'));
  clientType$ = new BehaviorSubject<string>('');

  constructor(private httpClient: HttpClient, private router: Router) {}

  public login(user: User): Observable<string> {
    const theHeaders = new HttpHeaders().set('Accept', 'text/plain');
    const options = { headers: theHeaders };

    console.log(user);
    return this.httpClient
      .post<string>('http://localhost:8080/login', user, options)
      .pipe(
        tap(() => {
          this.clientType$.next(user.clientType || '');
          this.isLogin$.next(true);
        })
      );
  }

  public logout(): void {
    sessionStorage.clear();
    this.isLogin$.next(false);
    this.clientType$.next('');

    alert('You are logged out!');

    this.router.navigateByUrl('/login');
  }
}
