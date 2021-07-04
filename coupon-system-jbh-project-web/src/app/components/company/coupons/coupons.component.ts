import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { CategoryTypes, Coupon } from 'src/app/models/coupon';
import { CompanyService } from 'src/app/service/company.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css'],
})
export class CouponsComponent implements OnInit {
  public coupon: Coupon[] = [];

  max = 0;

  category: CategoryTypes = CategoryTypes.DontUse;

  constructor(
    private companyService: CompanyService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Company | My Coupons');
    this.getAllCoupons();
  }

  // tslint:disable-next-line: typedef
  public getAllCoupons() {
    this.companyService.getAllCoupons().subscribe(
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
    this.companyService.getAllCouponsByCategory(this.category).subscribe(
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
    this.companyService.getAllCouponsByMaxPrice(this.max).subscribe(
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
