package com.orad.Project3.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.orad.Project3.exceptions.CouponSystemException;
import com.orad.Project3.models.Coupon;
import com.orad.Project3.models.Coupon.CategoryTypes;
import com.orad.Project3.models.Customer;
import com.orad.Project3.repositories.CouponRepository;
import com.orad.Project3.repositories.CustomerRepository;

@Service
@Transactional
@Scope("prototype")
public class CustomerService implements ClientService {

	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CustomerRepository customerRepository;
	private int customerId;

	// ctor
	public CustomerService() {
	}

	public CustomerService(int customerId) {
		super();
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	// methods
	@Override
	public boolean login(String email, String password) {// works
		if (customerRepository.existsByEmailAndPassword(email, password)) {
			Customer c = customerRepository.findByEmail(email);
			System.out.println("Customer Login Successful - Welcome " + c.getFirstName() + " " + c.getLastName());
			return true;
		}
		System.out.println("Customer Login Failed\nEmail or Password Do Not Match - Try Again");
		return false;
	}

	public int getCompanyIdByEmail(String email) {
		Customer c = customerRepository.findByEmail(email);
		int custId = c.getId();

		return custId;
	}

	public void purchaseCoupon(int couponId) throws CouponSystemException {// >>can't get the same coupon twice, must be
																			// in stock and in date+then
		// update amount
		Date now = new Date(System.currentTimeMillis());

		Customer customer = customerRepository.getOne(customerId);
		Coupon coupon = couponRepository.getOne(couponId);
		if (coupon.getAmount() > 0 && coupon.getEndDate().after(now)) {

			if (!couponRepository.existsByCustomersIdAndId(customerId, couponId)) {
				customer.addCoupon(coupon);
				coupon.setAmount(coupon.getAmount() - 1);
				couponRepository.save(coupon);
				System.out.println("Coupon Purchesed Successfully!");

			} else {
				System.err.println("Oops! Youv'e already purchased this coupon.\nLimited to 1 per customer.");
				throw new CouponSystemException();
			}
		} else {
			System.err.println("Oops! Failed to purchase coupon.\nSorry, it's either out of stock or expired. ");
			throw new CouponSystemException();
		}
	}

	public List<Coupon> getAllCustomerCoupons() {
		return couponRepository.findByCustomersId(customerId);
	}

	public List<Coupon> getAllCustomerCouponsByCategory(CategoryTypes category) throws CouponSystemException {
		try {
			Optional<Customer> cus = customerRepository.findById(customerId);
			if (cus.isPresent()) {
				return couponRepository.findByCustomersIdAndCategory(cus.get().getId(), category);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public List<Coupon> getAllCustomerCouponsByMaxPrice(double max) throws CouponSystemException {
		try {
			List<Coupon> coupons = couponRepository.findByCustomersIdAndPriceLessThanEqual(customerId, max);
			return coupons;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public Optional<Customer> getInfo() throws CouponSystemException {
		try {
			return customerRepository.findById(customerId);
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public Optional<Customer> getInfo(int id) throws CouponSystemException {
		try {
			return customerRepository.findById(customerId);
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public List<Coupon> getAllTheCoupons() throws CouponSystemException {
		try {
			return couponRepository.findAll();
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

}
