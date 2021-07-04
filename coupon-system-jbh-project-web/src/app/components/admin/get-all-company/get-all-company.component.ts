import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-get-all-company',
  templateUrl: './get-all-company.component.html',
  styleUrls: ['./get-all-company.component.css'],
})
export class GetAllCompanyComponent implements OnInit {
  public company: Company[] = [];

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Companies');
    this.getAllCompanies();
  }

  // tslint:disable-next-line: typedef
  public getAllCompanies() {
    this.adminService.getAllCompanies().subscribe(
      (c) => {
        this.company = c;
      },

      (e) => {
        console.log(e);
        alert('Failed to get companies info!');
      }
    );
  }
}
