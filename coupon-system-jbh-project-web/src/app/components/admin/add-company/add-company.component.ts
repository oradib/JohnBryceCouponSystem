import { Component, NgModule, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css'],
})
export class AddCompanyComponent implements OnInit {
  public company = new Company();

  constructor(
    private adminService: AdminService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit(): void {
    this.title.setTitle('Admin | Add Company');
  }

  // tslint:disable-next-line: typedef
  public addCompany() {
    this.adminService.addCompany(this.company).subscribe(
      (c) => {
        alert('Company added successfully');
        this.router.navigateByUrl('/Admin');
      },

      (e) => {
        console.log(e);
        alert(
          'A company by that name/email alreadyt exists! please retry with a different name/email.'
        );
      }
    );
  }
}
