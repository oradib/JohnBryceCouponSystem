import { HttpClient, HttpHeaders } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryTypes, Coupon } from '../models/coupon';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private httpClient: HttpClient) {}

  private url = 'http://localhost:8080/api/company/';

  public getInfo(): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<any>(this.url + 'getInfo', { headers: headers });
  }

  public addCoupon(coupon: Coupon): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.post<any>(this.url + 'addCoupon', coupon, {
      headers: headers,
    });
  }

  public updateCoupon(coupon: Coupon): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.put<any>(this.url + 'updateCoupon', coupon, {
      headers: headers,
    });
  }

  public deleteCoupon(id: number): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.delete<any>(
      this.url + 'deleteCoupon/{id}?id=' + id,
      { headers: headers }
    );
  }

  public getAllCoupons(): Observable<Coupon[]> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<Coupon[]>(this.url + 'getAllCoupons', {
      headers: headers,
    });
  }

  public getAllCouponsByCategory(
    category: CategoryTypes
  ): Observable<Coupon[]> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<Coupon[]>(
      this.url +
        'getAllCouponsByCategory/{categoryType}?categoryType=' +
        category,
      { headers: headers }
    );
  }

  public getAllCouponsByMaxPrice(max: number): Observable<Coupon[]> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<Coupon[]>(
      this.url + 'getAllCouponsByMaxPrice/{max}?max=' + max,
      { headers: headers }
    );
  }
}
