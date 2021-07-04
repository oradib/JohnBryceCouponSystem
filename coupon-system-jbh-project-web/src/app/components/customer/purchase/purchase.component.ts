import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Coupon } from 'src/app/models/coupon';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css'],
})
export class PurchaseComponent implements OnInit {
  id = 0;

  public coupons: Coupon[] = [];

  constructor(
    private customerService: CustomerService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Customer | Purchase');
    this.getAllTheCoupons();
  }

  // tslint:disable-next-line: typedef
  public purchaseCoupon() {
    if (this.id !== undefined) {
      this.customerService.purchaseCoupon(this.id).subscribe(
        (c) => {
          alert('Coupon purchased successfully!');
        },

        (e) => {
          console.log(e);
          alert(
            'Failed to complete your purchase! You\'ve either already purchased this coupon or it\'s out of stock.'
          );
        }
      );
    }
  }

  // tslint:disable-next-line: typedef
  public getAllTheCoupons() {
    this.customerService.getAllTheCoupons().subscribe(
      (c) => {
        this.coupons = c;
      },

      (e) => {
        console.log(e);
        alert('Oops! something went wrong..');
      }
    );
  }
}
