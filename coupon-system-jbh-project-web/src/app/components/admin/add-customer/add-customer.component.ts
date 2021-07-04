import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css'],
})
export class AddCustomerComponent implements OnInit {
  public customer = new Customer();

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Add Customer');
  }

  // tslint:disable-next-line: typedef
  public addCustomer() {
    this.adminService.addCustomer(this.customer).subscribe(
      (c) => {
        alert('Customer added successfully');
        this.router.navigateByUrl('/Admin');
      },

      (e) => {
        console.log(e);
        alert('Failed to add new customer!');
      }
    );
  }
}
