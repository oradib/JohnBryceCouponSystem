import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, Subject, throwError } from 'rxjs';
import { catchError, throttleTime } from 'rxjs/operators';
import { LoginService } from '../service/login.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  getSessionToken(): any {
    return sessionStorage.getItem('token');
  }

  private throttleLogout = new Subject();
  constructor(private loginService: LoginService) {
    this.throttleLogout.pipe(throttleTime(5000)).subscribe((url) => {
      this.loginService.logout();
    });
  }

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // request = request.clone({
    //     setHeaders: {
    //       'token': `${this.getSessionToken()}`
    //     }
    //   });
    return next.handle(request).pipe(
      catchError((response: HttpErrorResponse) => {
        if (response.status === 401) {
          this.throttleLogout.next();
        }
        return throwError(response);
      })
    );
  }
}
