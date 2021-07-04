import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { LayoutComponent } from './components/layout/layout.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ManuComponent } from './components/manu/manu.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { Page404Component } from './components/page404/page404.component';
import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/admin/admin.component';
import { CompanyComponent } from './components/company/company.component';
import { CustomerComponent } from './components/customer/customer.component';
import { AddCompanyComponent } from './components/admin/add-company/add-company.component';
import { AddCustomerComponent } from './components/admin/add-customer/add-customer.component';
import { UpdateCompanyComponent } from './components/admin/update-company/update-company.component';
import { UpdateCustomerComponent } from './components/admin/update-customer/update-customer.component';
import { DeleteCustomerComponent } from './components/admin/delete-customer/delete-customer.component';
import { DeleteCompanyComponent } from './components/admin/delete-company/delete-company.component';
import { GetOneCompanyComponent } from './components/admin/get-one-company/get-one-company.component';
import { GetOneCustomerComponent } from './components/admin/get-one-customer/get-one-customer.component';
import { GetAllCompanyComponent } from './components/admin/get-all-company/get-all-company.component';
import { GetAllCustomerComponent } from './components/admin/get-all-customer/get-all-customer.component';
import { AddComponent } from './components/company/add/add.component';
import { UpdateComponent } from './components/company/update/update.component';
import { CouponsComponent } from './components/company/coupons/coupons.component';
import { DeleteComponent } from './components/company/delete/delete.component';
import { CustomerCouponsComponent } from './components/customer/customer-coupons/customer-coupons.component';
import { CustomerProfileComponent } from './components/customer/customer-profile/customer-profile.component';
import { PurchaseComponent } from './components/customer/purchase/purchase.component';
import { CompanyProfileComponent } from './components/company/company-profile/company-profile.component';
import { TokenInterceptor } from './providers/token-interceptor';

@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    FooterComponent,
    ManuComponent,
    ContactComponent,
    AboutComponent,
    LoginComponent,
    Page404Component,
    HomeComponent,
    AdminComponent,
    CompanyComponent,
    CustomerComponent,
    AddCompanyComponent,
    AddCustomerComponent,
    UpdateCompanyComponent,
    UpdateCustomerComponent,
    DeleteCustomerComponent,
    DeleteCompanyComponent,
    GetOneCompanyComponent,
    GetOneCustomerComponent,
    GetAllCompanyComponent,
    GetAllCustomerComponent,
    AddComponent,
    UpdateComponent,
    CouponsComponent,
    DeleteComponent,
    CustomerCouponsComponent,
    CustomerProfileComponent,
    PurchaseComponent,
    CompanyProfileComponent,
  ],

  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
  ],
  bootstrap: [LayoutComponent],
})
export class AppModule {}
