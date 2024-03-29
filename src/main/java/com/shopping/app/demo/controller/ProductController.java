package com.shopping.app.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.app.demo.delegate.ProductDelegate;
import com.shopping.app.demo.entity.Coupon;
import com.shopping.app.demo.entity.ProductOrder;
import com.shopping.app.web.model.CouponSO;
import com.shopping.app.web.model.InventorySO;
import com.shopping.app.web.model.Orders;
import com.shopping.app.web.model.ProductOrderSO;

import io.micrometer.common.lang.Nullable;

@RestController
public class ProductController {
	
	@Autowired
	ProductDelegate productDelegate;

	@GetMapping("/api/inventory")
	public @ResponseBody InventorySO getInventory(@RequestParam @Nullable String ordered) {
		return productDelegate.getInventory(ordered);
	}
	
	@GetMapping("/api/fetchCoupons")
	public @ResponseBody CouponSO getFetchCoupons() {
		return productDelegate.getFetchCoupons();
	}
	
	@PostMapping("/api/{userId}/order")
	public ResponseEntity<?> createOrder(@PathVariable("userId") String userId, @RequestParam("qty")String quantity,
			@RequestParam("coupon")String coupon) {
		return productDelegate.createOrder(userId, quantity, coupon);
	}
	
	@PostMapping("/api/{userId}/{orderId}/pay")
	public ResponseEntity<?> makePayment(@PathVariable("userId") String userId,
			@PathVariable("orderId") String orderId, @RequestParam("amount")String amount) {
		return productDelegate.makePayment(userId, orderId,amount);
	}
	
	@GetMapping("/api/{userId}/orders")
	public List<Orders> getOrders(@PathVariable("userId") String userId) {
		return productDelegate.getOrders();
	}
	@GetMapping("/api/{userId}/orders/{orderId}")
	public ResponseEntity<?> getOrdersByOrderId(@PathVariable("userId") String userId,@PathVariable("orderId") String orderId) {
		return productDelegate.getOrdersByOrderId(orderId);
	}
}
