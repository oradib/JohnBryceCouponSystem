package com.orad.Project3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.orad.Project3.login.LoginManager.ClientType;
import com.orad.Project3.models.Coupon;
import com.orad.Project3.models.Coupon.CategoryTypes;
import com.orad.Project3.models.Customer;
import com.orad.Project3.service.CustomerService;
import com.orad.Project3.session.SessionContext;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController extends ClientController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private SessionContext sessionContext;
	private ClientType clientType = ClientType.Customer;

	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/purchaseCoupon/{id}")
	public void purchaseCoupon(@RequestParam int id, @RequestHeader String token) {
		try {
			System.out.println("Hello");
			if (verifyToken(token, sessionContext, clientType)) {
				customerService = (CustomerService) sessionContext.getSession(token).getService();
				customerService.purchaseCoupon(id);
			} else {
				System.out.println("Hello hello");
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getAllCoupons")
	public List<Coupon> getAllCoupons(@RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				customerService = (CustomerService) sessionContext.getSession(token).getService();
				return customerService.getAllCustomerCoupons();
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getAllCouponsByCategory/{categoryType}")
	public List<Coupon> getAllCouponsByCategory(@RequestParam CategoryTypes categoryType, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				customerService = (CustomerService) sessionContext.getSession(token).getService();
				return customerService.getAllCustomerCouponsByCategory(categoryType);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getAllCouponsByMaxPrice/{max}")
	public List<Coupon> getAllCouponsByMaxPrice(@RequestParam double max, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				customerService = (CustomerService) sessionContext.getSession(token).getService();
				return customerService.getAllCustomerCouponsByMaxPrice(max);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getInfo")
	public Optional<Customer> getCompany(@RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				customerService = (CustomerService) sessionContext.getSession(token).getService();
				return customerService.getInfo(customerService.getCustomerId());
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getAllTheCoupons")
	public List<Coupon> getAllTheCoupons(@RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				customerService = (CustomerService) sessionContext.getSession(token).getService();
				return customerService.getAllTheCoupons();

			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

}
