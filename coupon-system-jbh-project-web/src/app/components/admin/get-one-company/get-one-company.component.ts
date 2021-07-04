import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-get-one-company',
  templateUrl: './get-one-company.component.html',
  styleUrls: ['./get-one-company.component.css'],
})
export class GetOneCompanyComponent implements OnInit {
  public company = new Company();

  id = 0;

  public companies: Company[] = [];

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Company Info');
    this.getAllCompanies();
  }

  // tslint:disable-next-line: typedef
  public getCompany() {
    if (this.id !== undefined) {
      this.adminService.getCompany(this.id).subscribe(
        (c) => {
          this.company = c;
        },

        (e) => {
          console.log(e);
          alert('Failed to get company info!');
        }
      );
    }
  }

  // tslint:disable-next-line: typedef
  public getAllCompanies() {
    this.adminService.getAllCompanies().subscribe(
      (c) => {
        this.companies = c;
      },

      (e) => {
        console.log(e);
        alert('Oops! something went wrong..');
      }
    );
  }
}
