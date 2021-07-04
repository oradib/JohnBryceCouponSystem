import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css'],
})
export class UpdateCompanyComponent implements OnInit {
  public company = new Company();

  public companies: Company[] = [];

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Company Update');
    this.getAllCompanies();
  }

  // tslint:disable-next-line: typedef
  public updateCompany() {
    this.adminService.updateCompany(this.company).subscribe(
      (c) => {
        alert('Company updated successfully');
        this.router.navigateByUrl('/Admin');
      },

      (e) => {
        console.log(e);
        alert('Failed to update company! Company name cannot be changed.');
      }
    );
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
