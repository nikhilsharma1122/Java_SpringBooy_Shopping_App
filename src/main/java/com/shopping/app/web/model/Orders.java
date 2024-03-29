package com.shopping.app.web.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Orders {
	private long orderId;
	private int amount;
	private Date orderDate;
	private String coupon;
	private String transactionId;
	private String status;
	 public Orders(long orderId, int amount, Date orderDate, String coupon){
		this.orderId = orderId;
		this.amount = amount;
		this.orderDate = orderDate;
		this.coupon = coupon;
	}
	public Orders(long orderId, int amount, Date orderDate, String coupon, String transactionId, String status){
		this.orderId = orderId;
		this.amount = amount;
		this.orderDate = orderDate;
		this.coupon = coupon;
		this.transactionId = transactionId;
		this.status =status;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
