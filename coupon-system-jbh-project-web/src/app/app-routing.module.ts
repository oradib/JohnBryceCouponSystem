import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { AddCompanyComponent } from './components/admin/add-company/add-company.component';
import { AddCustomerComponent } from './components/admin/add-customer/add-customer.component';
import { AdminComponent } from './components/admin/admin.component';
import { DeleteCompanyComponent } from './components/admin/delete-company/delete-company.component';
import { DeleteCustomerComponent } from './components/admin/delete-customer/delete-customer.component';
import { GetAllCompanyComponent } from './components/admin/get-all-company/get-all-company.component';
import { GetAllCustomerComponent } from './components/admin/get-all-customer/get-all-customer.component';
import { GetOneCompanyComponent } from './components/admin/get-one-company/get-one-company.component';
import { GetOneCustomerComponent } from './components/admin/get-one-customer/get-one-customer.component';
import { UpdateCompanyComponent } from './components/admin/update-company/update-company.component';
import { UpdateCustomerComponent } from './components/admin/update-customer/update-customer.component';
import { AddComponent } from './components/company/add/add.component';
import { CompanyProfileComponent } from './components/company/company-profile/company-profile.component';
import { CompanyComponent } from './components/company/company.component';
import { CouponsComponent } from './components/company/coupons/coupons.component';
import { DeleteComponent } from './components/company/delete/delete.component';
import { UpdateComponent } from './components/company/update/update.component';
import { ContactComponent } from './components/contact/contact.component';
import { CustomerCouponsComponent } from './components/customer/customer-coupons/customer-coupons.component';
import { CustomerProfileComponent } from './components/customer/customer-profile/customer-profile.component';
import { CustomerComponent } from './components/customer/customer.component';
import { PurchaseComponent } from './components/customer/purchase/purchase.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { Page404Component } from './components/page404/page404.component';

const routes: Routes = [
  { path: 'contact', component: ContactComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'login', component: LoginComponent },
  { path: 'Admin', component: AdminComponent },
  { path: 'admin/add-company', component: AddCompanyComponent },
  { path: 'admin/add-customer', component: AddCustomerComponent },
  { path: 'admin/update-company', component: UpdateCompanyComponent },
  { path: 'admin/update-customer', component: UpdateCustomerComponent },
  { path: 'admin/delete-company', component: DeleteCompanyComponent },
  { path: 'admin/delete-customer', component: DeleteCustomerComponent },
  { path: 'admin/get-one-company', component: GetOneCompanyComponent },
  { path: 'admin/get-one-customer', component: GetOneCustomerComponent },
  { path: 'admin/get-all-company', component: GetAllCompanyComponent },
  { path: 'admin/get-all-customer', component: GetAllCustomerComponent },
  { path: 'Company', component: CompanyComponent },
  { path: 'company/add', component: AddComponent },
  { path: 'company/delete', component: DeleteComponent },
  { path: 'company/coupons', component: CouponsComponent },
  { path: 'company/update', component: UpdateComponent },
  { path: 'company/update', component: UpdateComponent },
  { path: 'company/company-profile', component: CompanyProfileComponent },
  { path: 'Customer', component: CustomerComponent },
  { path: 'customer/customer-coupons', component: CustomerCouponsComponent },
  { path: 'customer/customer-profile', component: CustomerProfileComponent },
  { path: 'customer/purchase', component: PurchaseComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: Page404Component },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
