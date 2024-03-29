package com.shopping.app.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.app.demo.entity.Coupon;
import com.shopping.app.demo.entity.ProductOrder;
import com.shopping.app.web.model.CouponSO;
import com.shopping.app.web.model.InventorySO;
import com.shopping.app.web.model.Orders;
import com.shopping.app.web.model.ProductOrderSO;

@Service
public interface InventoryService {
	InventorySO getInventory(String ordered);

	CouponSO getFetchCoupons();

	ResponseEntity<?> createOrder(String userId, String quantity, String coupon);

	ResponseEntity<?> makePayment(String userId, String orderId, String amount);

	List<Orders> getOrders();

	ResponseEntity<?> getOrdersByOrderId(String orderId);
}
