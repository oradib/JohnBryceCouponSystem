import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryTypes, Coupon } from '../models/coupon';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private url = 'http://localhost:8080/api/customer/';

  constructor(private httpClient: HttpClient) {}

  public purchaseCoupon(id: number): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.post<any>(
      this.url + 'purchaseCoupon/{id}?id=' + id,
      id,
      { headers: headers }
    );
  }

  public getInfo(): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<any>(this.url + 'getInfo', { headers: headers });
  }

  public getAllCoupons(): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<any>(this.url + 'getAllCoupons', {
      headers: headers,
    });
  }

  public getAllCouponsByCategory(category: CategoryTypes): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<any>(
      this.url +
        'getAllCouponsByCategory/{categoryType}?categoryType=' +
        category,
      { headers: headers }
    );
  }

  public getAllCouponsByMaxPrice(max: number): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<any>(
      this.url + 'getAllCouponsByMaxPrice/{max}?max=' + max,
      { headers: headers }
    );
  }

  public getAllTheCoupons(): Observable<Coupon[]> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<Coupon[]>(this.url + 'getAllTheCoupons', {
      headers: headers,
    });
  }
}
