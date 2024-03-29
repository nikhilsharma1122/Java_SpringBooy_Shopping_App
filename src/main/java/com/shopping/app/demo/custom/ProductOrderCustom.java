package com.shopping.app.demo.custom;

import java.util.Date;

public interface ProductOrderCustom {
	public void saveProductOrder(long orderId,String coupon, int amount, Date orderDate,String transaction,String status, int userId);
}
