import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Coupon } from 'src/app/models/coupon';
import { CompanyService } from 'src/app/service/company.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css'],
})
export class AddComponent implements OnInit {
  public coupon = new Coupon();

  constructor(
    private companyService: CompanyService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Company | Add Coupon');
  }

  // tslint:disable-next-line: typedef
  public addCoupon() {
    this.companyService.addCoupon(this.coupon).subscribe(
      (c) => {
        alert('Coupon added successfully');
        this.router.navigateByUrl('/Company');
      },

      (e) => {
        console.log(e);
        alert(
          'Failed to add coupon! You already have a coupon with this title, use a different title.'
        );
      }
    );
  }
}
