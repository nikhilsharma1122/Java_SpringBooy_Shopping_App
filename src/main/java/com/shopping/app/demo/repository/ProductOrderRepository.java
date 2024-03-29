package com.shopping.app.demo.repository;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopping.app.demo.entity.Coupon;
import com.shopping.app.demo.entity.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
	
	public ProductOrder findByUserId(int userId);
	public ProductOrder findByOrderId(long orderId);
	@Query("select o from ProductOrder o where o.orderId=:orderId")
	public List<ProductOrder> getOrderId(@Param("orderId") long orderId);
	@Query("select o from ProductOrder o")
	public List<ProductOrder> getOrders(); 
}
