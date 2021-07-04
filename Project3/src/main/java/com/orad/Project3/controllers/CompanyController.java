package com.orad.Project3.controllers;

import java.util.List;
import java.util.Optional;

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
import com.orad.Project3.models.Coupon;
import com.orad.Project3.models.Coupon.CategoryTypes;
import com.orad.Project3.service.CompanyService;
import com.orad.Project3.session.SessionContext;

@RestController
@RequestMapping("/api/company")
@CrossOrigin
public class CompanyController extends ClientController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private SessionContext sessionContext;
	private ClientType clientType = ClientType.Company;

	@Autowired
	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@PostMapping("/addCoupon")
	public void addCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				companyService = (CompanyService) sessionContext.getSession(token).getService();
				companyService.addCoupon(coupon);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@PutMapping("/updateCoupon")
	public void updateCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				companyService = (CompanyService) sessionContext.getSession(token).getService();
				companyService.updateCoupon(coupon);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@DeleteMapping("/deleteCoupon/{id}")
	public void deleteCoupon(@RequestParam int id, @RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				companyService = (CompanyService) sessionContext.getSession(token).getService();
				companyService.deleteCoupon(id);
			} else {
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
				companyService = (CompanyService) sessionContext.getSession(token).getService();
				return companyService.getAllCoupons();
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
				companyService = (CompanyService) sessionContext.getSession(token).getService();
				return companyService.getAllCouponsByCategory(categoryType);
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
				companyService = (CompanyService) sessionContext.getSession(token).getService();
				return companyService.getAllCouponsByMaxPrice(max);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}

	@GetMapping("/getInfo")
	public Optional<Company> getCompany(@RequestHeader String token) {
		try {
			if (verifyToken(token, sessionContext, clientType)) {
				companyService = (CompanyService) sessionContext.getSession(token).getService();
				return companyService.getInfo(companyService.getCompanyId());
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautherized Request");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}
}
