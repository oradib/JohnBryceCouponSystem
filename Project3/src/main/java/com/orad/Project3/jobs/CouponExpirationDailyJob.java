package com.orad.Project3.jobs;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.orad.Project3.models.Coupon;
import com.orad.Project3.repositories.CouponRepository;

@Component
public class CouponExpirationDailyJob extends Thread {

	@Autowired
	private CouponRepository couponRepository;
	private boolean quit = false;

	public CouponExpirationDailyJob() {
	};

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	@PostConstruct
	public void initJob() {
		start();
		System.out.println("(CouponExpirationDailyJob Start)");
	}

	@PreDestroy
	public void destroyJob() {
		setQuit(true);
		interrupt();
		System.out.println("(CouponExpirationDailyJob Ended)");
		System.out.println("            .•:*¨¨*:•.Thank you for using Coupons'R'Us.•:*¨¨*:•. \n\n..Goodbye");
	}

	@Override
	@Transactional
	public void run() {
		while (!quit) {
			Date now = Date.valueOf(LocalDate.now());
			List<Coupon> couponsByEndDate = couponRepository.findByEndDateBefore(now);
			if (!couponsByEndDate.isEmpty()) { // leave just the foreach
				for (Coupon coupon : couponsByEndDate) {
					couponRepository.deleteById(coupon.getId());
					System.out.println("***An Expired Coupon Was Deleted***");
				}
			} else {
				System.out.println("***No Expired Coupons Were Deleted***");
			}
			try {
				sleep(86_400_000);
			} catch (InterruptedException e) {
				// this exception will only arise while sleeping (not in action), doesn't matter
				// if I interrupt it
			}
		}

	}

}
