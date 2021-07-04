package com.orad.Project3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.orad.Project3.login.LoginManager.ClientType;
import com.orad.Project3.models.Company;
import com.orad.Project3.models.Customer;
import com.orad.Project3.service.AdminService;
import com.orad.Project3.session.SessionContext;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController extends ClientController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private SessionContext sessionContext;
	private ClientType clientType = ClientType.Admin;

	@Autowired
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@PostMapping("/addCompany")
	public void addCompany(@RequestBody Company company, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				adminService.addCompany(company);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@PutMapping("/updateCompany")
	public void updateCompany(@RequestBody Company company, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				adminService.updateCompany(company);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@DeleteMapping("/deleteCompany/{id}")
	public void deleteCompany(@RequestParam int id, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				adminService.deleteCompany(id);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getCompany/{id}")
	public Company getCompany(@RequestParam int id, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				return adminService.getCompanyById(id);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getAllCompanies")
	public List<Company> getAllCompanies(@RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				return adminService.getAllCompanies();
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@PostMapping("/addCustomer")
	public void addCustomer(@RequestBody Customer customer, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				adminService.addCustomer(customer);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@PutMapping("/updateCustomer")
	public void updateCustomer(@RequestBody Customer customer, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				adminService.updateCustomer(customer);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@DeleteMapping("/deleteCustomer/{id}")
	public void deleteCustomer(@RequestParam int id, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				adminService.deleteCustomer(id);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getAllCustomers")
	public List<Customer> getAllCustomers(@RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				return adminService.getAllCustomers();
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getCustomer/{id}")
	public Customer getCustomerById(@RequestParam int id, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				adminService = (AdminService) sessionContext.getSession(token).getService();
				return adminService.getCustomerById(id);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

}
