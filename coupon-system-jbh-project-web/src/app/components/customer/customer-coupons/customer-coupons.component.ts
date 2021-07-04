import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { CategoryTypes, Coupon } from 'src/app/models/coupon';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-customer-coupons',
  templateUrl: './customer-coupons.component.html',
  styleUrls: ['./customer-coupons.component.css'],
})
export class CustomerCouponsComponent implements OnInit {
  public coupon: Coupon[] = [];

  max = 0;

  category: CategoryTypes = CategoryTypes.DontUse;

  constructor(
    private customerService: CustomerService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Customer | My Coupons');
    this.getAllCoupons();
  }

  // tslint:disable-next-line: typedef
  public getAllCoupons() {
    this.customerService.getAllCoupons().subscribe(
      (c) => {
        this.coupon = c;
      },

      (e) => {
        console.log(e);
        alert('Failed to get coupons info!');
      }
    );
  }

  // tslint:disable-next-line: typedef
  public getAllCouponsByCategory() {
    this.customerService.getAllCouponsByCategory(this.category).subscribe(
      (c) => {
        this.coupon = c;
      },

      (e) => {
        console.log(e);
        alert('Oops! something went wrong..');
      }
    );
  }

  // tslint:disable-next-line: typedef
  public getAllCouponsByMaxPrice() {
    this.customerService.getAllCouponsByMaxPrice(this.max).subscribe(
      (c) => {
        this.coupon = c;
      },

      (e) => {
        console.log(e);
        alert('Oops! something went wrong..');
      }
    );
  }
}
