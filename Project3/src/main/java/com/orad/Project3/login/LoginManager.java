package com.orad.Project3.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.orad.Project3.service.AdminService;
import com.orad.Project3.service.ClientService;
import com.orad.Project3.service.CompanyService;
import com.orad.Project3.service.CustomerService;

@Service
public class LoginManager {

	public enum ClientType {
		Admin, Company, Customer;

	}

	@Autowired
	private ApplicationContext ctx;

	public LoginManager() {

	}

	public ClientService login(String email, String password, ClientType clientType) {// if login==true by client.type
		if (clientType == ClientType.Admin) {
			AdminService admin = ctx.getBean(AdminService.class);
			if (admin.login(email, password)) {
				System.out.println("Admin login successful");
				return admin;
			}
		}
		if (clientType == ClientType.Company) {
			CompanyService company = ctx.getBean(CompanyService.class);
			if (company.login(email, password)) {

				company.setCompanyId(company.getCompanyIdByEmail(email));
				System.out.println("Company login successful");
				return company;
			}
		}
		if (clientType == ClientType.Customer) {
			CustomerService customer = ctx.getBean(CustomerService.class);
			if (customer.login(email, password)) {
				customer.setCustomerId(customer.getCompanyIdByEmail(email));
				System.out.println("Customer login successful");
				return customer;
			}
		}
		// if nothing sticks return null
		return null;

	}

}
