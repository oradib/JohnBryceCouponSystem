import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Coupon } from 'src/app/models/coupon';
import { CompanyService } from 'src/app/service/company.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css'],
})
export class DeleteComponent implements OnInit {
  public id = 0;

  public coupons: Coupon[] = [];

  constructor(
    private companyService: CompanyService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Company | Delete Coupon');
    this.getAllCoupons();
  }

  // tslint:disable-next-line: typedef
  public deleteCoupon() {
    this.companyService.deleteCoupon(this.id).subscribe(
      (c) => {
        alert('Coupon deleted successfully');
        this.router.navigateByUrl('/Company');
      },

      (e) => {
        console.log(e);
        alert('Failed to delete coupon!');
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
        alert('Oops! something went wrong..');
      }
    );
  }
}
