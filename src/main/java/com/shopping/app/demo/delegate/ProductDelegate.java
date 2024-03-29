package com.shopping.app.demo.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.shopping.app.demo.entity.Coupon;
import com.shopping.app.demo.entity.ProductOrder;
import com.shopping.app.demo.service.InventoryService;
import com.shopping.app.web.model.CouponSO;
import com.shopping.app.web.model.InventorySO;
import com.shopping.app.web.model.Orders;
import com.shopping.app.web.model.ProductOrderSO;

@Component
public class ProductDelegate {
	
	@Autowired
	InventoryService inventoryService;
	
	public InventorySO getInventory(String ordered) {
		return inventoryService.getInventory(ordered);
	}

	public CouponSO getFetchCoupons() {
		// TODO Auto-generated method stub
		return inventoryService.getFetchCoupons();
	}

	public ResponseEntity<?> createOrder(String userId, String quantity, String coupon) {
		// TODO Auto-generated method stub
		return inventoryService.createOrder(userId, quantity, coupon);
	}

	public ResponseEntity<?> makePayment(String userId, String orderId, String amount) {
		// TODO Auto-generated method stub
		return inventoryService.makePayment(userId, orderId,amount);
	}

	public List<Orders> getOrders() {
		// TODO Auto-generated method stub
		return inventoryService.getOrders();
	}

	public ResponseEntity<?> getOrdersByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return inventoryService.getOrdersByOrderId(orderId);
	}
}
