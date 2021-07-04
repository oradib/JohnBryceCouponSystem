package com.orad.Project3.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orad.Project3.models.Coupon;
import com.orad.Project3.models.Coupon.CategoryTypes;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	List<Coupon> findByCustomersId(int couponId);

	List<Coupon> findByCustomersIdAndCategory(int customersId, CategoryTypes category);

	List<Coupon> findByCustomersIdAndPriceLessThanEqual(int customerId, double max);

	List<Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId, double max);

	List<Coupon> findByCompanyId(int companyId);

	List<Coupon> findByCompanyIdAndCategory(int companyId, CategoryTypes categoryType);

	List<Coupon> findByEndDateBefore(Date date);

	boolean existsByCustomersIdAndId(int couponId, int id);

	boolean existsByCompanyIdAndTitle(int companyId, String title);

}
