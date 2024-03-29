package com.shopping.app.demo.entity;



import java.util.Date;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="product_order")
public class ProductOrder {

	@Id
	@Column(name="order_id")
	private long orderId;
	@Column(name="coupon")
	private String coupon;
	@Column(name="amount")
	private int amount;
	@Column(name="order_date")
	private Date orderDate;
	@Column(name="transaction")
	@Nullable
	private String transaction;
	@Column(name="status")
	private String status;
	@Column(name="user_id")
	private int userId;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
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
	public void setOrderDate(java.util.Date date) {
		this.orderDate = date;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
