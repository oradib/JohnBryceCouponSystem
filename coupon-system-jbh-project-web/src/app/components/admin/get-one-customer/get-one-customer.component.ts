import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-get-one-customer',
  templateUrl: './get-one-customer.component.html',
  styleUrls: ['./get-one-customer.component.css'],
})
export class GetOneCustomerComponent implements OnInit {
  public customer = new Customer();

  id = 0;

  public customers: Customer[] = [];

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Customer Info');
    this.getAllCustomer();
  }

  // tslint:disable-next-line: typedef
  public getCustomer() {
    if (this.id !== undefined) {
      this.adminService.getCustomer(this.id).subscribe(
        (c) => {
          this.customer = c;
        },

        (e) => {
          console.log(e);
          alert('Failed to get customer info!');
        }
      );
    }
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
