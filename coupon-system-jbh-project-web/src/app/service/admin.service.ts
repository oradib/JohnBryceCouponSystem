import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../models/company';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private url = 'http://localhost:8080/api/admin/';

  public constructor(private httpClient: HttpClient) {}

  public getCompany(id: number): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<any>(this.url + 'getCompany/{id}?id=' + id, {
      headers: headers,
    });
  }

  public getAllCompanies(): Observable<Company[]> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<Company[]>(this.url + 'getAllCompanies', {
      headers: headers,
    });
  }

  public addCompany(company: Company): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.post<any>(this.url + 'addCompany', company, {
      headers: headers,
    });
  }

  public updateCompany(company: Company): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.put<any>(this.url + 'updateCompany', company, {
      headers: headers,
    });
  }

  public deleteCompany(id: number): Observable<Company> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.delete<any>(
      this.url + 'deleteCompany/{id}?id=' + id,
      { headers: headers }
    );
  }

  public addCustomer(customer: Customer): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.post<any>(this.url + 'addCustomer', customer, {
      headers: headers,
    });
  }

  public updateCustomer(customer: Customer): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.put<any>(this.url + 'updateCustomer', customer, {
      headers: headers,
    });
  }

  public deleteCustomer(id: number): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.delete<any>(
      this.url + 'deleteCustomer/{id}?id=' + id,
      { headers: headers }
    );
  }

  public getCustomer(id: number): Observable<any> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<any>(this.url + 'getCustomer/{id}?id=' + id, {
      headers: headers,
    });
  }

  public getAllCustomer(): Observable<Customer[]> {
    let headers = new HttpHeaders();
    // tslint:disable-next-line: no-non-null-assertion
    headers = headers.set('token', sessionStorage.getItem('token')!);
    let token = sessionStorage.getItem('token');
    return this.httpClient.get<Customer[]>(this.url + 'getAllCustomers', {
      headers: headers,
    });
  }
}
