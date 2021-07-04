import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css'],
})
export class UpdateCustomerComponent implements OnInit {
  public customer = new Customer();

  public customers: Customer[] = [];

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Customer Update');
    this.getAllCustomer();
  }

  // tslint:disable-next-line: typedef
  public updateCustomer() {
    this.adminService.updateCustomer(this.customer).subscribe(
      (c) => {
        alert('Customer updated successfully');
        this.router.navigateByUrl('/Admin');
      },

      (e) => {
        console.log(e);
        alert('Failed to update customer!');
      }
    );
  }

  // tslint:disable-next-line: typedef
  public getAllCustomer() {
    this.adminService.getAllCustomer().subscribe(
      (c) => {
        this.customers = c;
      },

      (e) => {
        console.log(e);
        alert('Oops! something went wrong..');
      }
    );
  }
}
