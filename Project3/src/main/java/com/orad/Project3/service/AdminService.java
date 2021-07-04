package com.orad.Project3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.orad.Project3.exceptions.CouponSystemException;
import com.orad.Project3.models.Company;
import com.orad.Project3.models.Customer;
import com.orad.Project3.repositories.CompanyRepository;
import com.orad.Project3.repositories.CustomerRepository;

@Service
@Transactional
@Scope("prototype")
public class AdminService implements ClientService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;

	public boolean login(String email, String password) {
		if (password.contentEquals("admin") && email.contentEquals("admin@admin.com"))
			return true;
		else
			return false;
	}

	public void addCompany(Company company) throws CouponSystemException {
		if (!companyRepository.existsByName(company.getName())
				&& !companyRepository.existsByEmail(company.getEmail())) {
			companyRepository.save(company);
			System.out.println("Add Company Was Successful");
		} else {
			System.err.println("Add Company Failed\nCompany Name or Email Already Exsits");
			throw new CouponSystemException();
		}
	}

	public void updateCompany(Company company) throws CouponSystemException {
		Optional<Company> c = companyRepository.findById(company.getId());
		if (c.get().getName().equalsIgnoreCase(company.getName())) {
			companyRepository.save(company);
			System.out.println("Update Company Successful");
		} else {
			System.err.println("Cannot Update Company Name");
			throw new CouponSystemException();
		}

	}

	public void deleteCompany(int companyId) throws CouponSystemException {// delete company +it's coupons and purchase
																			// history
		try {
			companyRepository.deleteById(companyId);
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public List<Company> getAllCompanies() throws CouponSystemException {
		try {
			List<Company> companies = companyRepository.findAll();
			return companies;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public Company getCompanyById(int id) throws CouponSystemException {
		try {
			Optional<Company> opt = companyRepository.findById(id);
			if (opt.isPresent()) {
				return opt.get();
			}
			return null;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public void addCustomer(Customer customer) throws CouponSystemException {
		if (!customerRepository.existsByEmail(customer.getEmail())) {
			customerRepository.save(customer);
		} else {
			System.err.println("Add Customer Failed\nA Customer With This Email Already Exsits");
			throw new CouponSystemException();
		}
	}

	public void updateCustomer(Customer customer) throws CouponSystemException {// can't change id
		try {
			Optional<Customer> c = customerRepository.findById(customer.getId());
			if (c.get().getId() == customer.getId()) {
				customerRepository.save(customer);
				System.out.println("Update Customer Successful");
			} else {
				System.err.println("Cannot Update Company Name");// can't happen in my client...
			}
		} catch (Exception e) {
			throw new CouponSystemException();
		}

	}

	public void deleteCustomer(int customerId) throws CouponSystemException { // delete customer +purchase history
		try {
			customerRepository.deleteById(customerId);
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public List<Customer> getAllCustomers() throws CouponSystemException {
		try {
			List<Customer> customers = customerRepository.findAll();
			return customers;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public Customer getCustomerById(int id) throws CouponSystemException {
		try {
			Optional<Customer> opt = customerRepository.findById(id);
			if (opt.isPresent()) {
				return opt.get();
			}
			return null;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

}
