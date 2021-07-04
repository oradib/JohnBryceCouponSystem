import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-delete-company',
  templateUrl: './delete-company.component.html',
  styleUrls: ['./delete-company.component.css'],
})
export class DeleteCompanyComponent implements OnInit {
  public id = 0;

  public companies: Company[] = [];

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Delete Company');
    this.getAllCompanies();
  }

  // tslint:disable-next-line: typedef
  public deleteCompany() {
    this.adminService.deleteCompany(this.id).subscribe(
      (c) => {
        alert('Company deleted successfully');
        this.router.navigateByUrl('/Admin');
      },

      (e) => {
        console.log(e);
        alert('Failed to delete company!');
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
