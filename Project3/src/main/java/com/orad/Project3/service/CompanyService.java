package com.orad.Project3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.orad.Project3.exceptions.CouponSystemException;
import com.orad.Project3.models.Company;
import com.orad.Project3.models.Coupon;
import com.orad.Project3.models.Coupon.CategoryTypes;
import com.orad.Project3.repositories.CompanyRepository;
import com.orad.Project3.repositories.CouponRepository;

@Service
@Transactional
@Scope("prototype")
public class CompanyService implements ClientService {

	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CompanyRepository companyRepository;
	private int companyId;

	// ctor
	public CompanyService() {
	}

	public CompanyService(int companyId) {
		this.companyId = companyId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	// methods
	@Override
	public boolean login(String email, String password) {
		if (companyRepository.existsByEmailAndPassword(email, password)) {
			System.out.println("Company Login Successful");
			return true;
		} else {
			System.err.println("Company Login Failed\nEmail or Password Do Not Match - Try Again");
			return false;
		}
	}

	public int getCompanyIdByEmail(String email) {
		Company comp = companyRepository.findByEmail(email);
		int companyId = comp.getId();
		return companyId;
	}

	public void addCoupon(Coupon coupon) throws CouponSystemException {
		if (!couponRepository.existsByCompanyIdAndTitle(companyId, coupon.getTitle())) {
			coupon.setCompany(companyRepository.getOne(companyId));
			couponRepository.save(coupon);
			System.out.println("Coupon Added Successfully");
		} else {
			System.err.println("Coupon Title Already Used - Use Another Title");
			throw new CouponSystemException();
		}
	}

	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		Optional<Coupon> c = couponRepository.findById(coupon.getId());
		if (c.isPresent()) {
			coupon.setCompany(companyRepository.getOne(companyId));
			couponRepository.save(coupon);
			System.out.println("Coupon Updated Successfully");
		} else {
			System.err.println("Update Coupon Failed - Only Update Coupons With The Same Company ID ");
			throw new CouponSystemException();
		}
	}

	public void deleteCoupon(int couponId) throws CouponSystemException {// deleteCoupon +delete purchase history
		try {
			couponRepository.deleteById(couponId);
			System.out.println("Coupon Deleted");
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public List<Coupon> getAllCoupons() throws CouponSystemException {
		try {
			List<Coupon> coupons = couponRepository.findByCompanyId(companyId);
			return coupons;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public List<Coupon> getAllCouponsForCustomer() throws CouponSystemException {
		try {
			List<Coupon> coupons = couponRepository.findAll();
			return coupons;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public List<Coupon> getAllCouponsByCategory(CategoryTypes categoryType) throws CouponSystemException {
		try {
			List<Coupon> coupons = couponRepository.findByCompanyIdAndCategory(companyId, categoryType);
			return coupons;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public List<Coupon> getAllCouponsByMaxPrice(double max) throws CouponSystemException {
		try {
			List<Coupon> coupons = couponRepository.findByCompanyIdAndPriceLessThanEqual(companyId, max);
			return coupons;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public Optional<Company> getInfo() throws CouponSystemException {
		try {
			if (companyRepository.findById(companyId) != null) {
				return companyRepository.findById(companyId);
			}
			return null;
		} catch (Exception e) {
			throw new CouponSystemException();
		}
	}

	public Optional<Company> getInfo(int id) {
		return companyRepository.findById(companyId);
	}

	public int findIdByEmail(String email) {
		Company comp = companyRepository.findByEmail(email);
		return comp.getId();
	}

}
