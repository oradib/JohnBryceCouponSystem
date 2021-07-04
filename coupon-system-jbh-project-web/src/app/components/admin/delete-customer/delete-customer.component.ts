import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-delete-customer',
  templateUrl: './delete-customer.component.html',
  styleUrls: ['./delete-customer.component.css'],
})
export class DeleteCustomerComponent implements OnInit {
  public id = 0;

  public customers: Customer[] = [];

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Delete Customer');
    this.getAllCustomer();
  }

  // tslint:disable-next-line: typedef
  public deleteCustomer() {
    this.adminService.deleteCustomer(this.id).subscribe(
      (c) => {
        alert('Customer deleted successfully');
        this.router.navigateByUrl('/Admin');
      },

      (e) => {
        console.log(e);
        alert('Failed to delete customer!');
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
