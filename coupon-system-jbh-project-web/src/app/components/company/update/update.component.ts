import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Coupon } from 'src/app/models/coupon';
import { CompanyService } from 'src/app/service/company.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css'],
})
export class UpdateComponent implements OnInit {
  coupon: Coupon = new Coupon();

  public coupons: Coupon[] = [];

  constructor(
    private companyService: CompanyService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Company | Coupon Update');
    this.getAllCoupons();
  }

  // tslint:disable-next-line: typedef
  public updateCoupon() {
    this.companyService.updateCoupon(this.coupon).subscribe(
      (c) => {
        alert('Coupon updated successfully');
        this.router.navigateByUrl('/Company');
      },

      (e) => {
        console.log(e);
        alert('Coupon update failed!');
      }
    );
  }

  // tslint:disable-next-line: typedef
  public getAllCoupons() {
    this.companyService.getAllCoupons().subscribe(
      (c) => {
        this.coupons = c;
      },

      (e) => {
        console.log(e);
      }
    );
  }
}
