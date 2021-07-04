import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-get-all-customer',
  templateUrl: './get-all-customer.component.html',
  styleUrls: ['./get-all-customer.component.css'],
})
export class GetAllCustomerComponent implements OnInit {
  public customer: Customer[] = [];

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Customers');
    this.getAllCustomer();
  }

  // tslint:disable-next-line: typedef
  public getAllCustomer() {
    this.adminService.getAllCustomer().subscribe(
      (c) => {
        this.customer = c;
      },

      (e) => {
        console.log(e);
        alert('Failed to get customers info!');
      }
    );
  }
}
