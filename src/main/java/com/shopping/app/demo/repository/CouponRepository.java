package com.shopping.app.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.app.demo.entity.Coupon;
import com.shopping.app.demo.entity.Inventory;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
	public Coupon findByCouponCode(String couponCode);
}
