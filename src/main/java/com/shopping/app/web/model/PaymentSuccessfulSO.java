package com.shopping.app.web.model;

public class PaymentSuccessfulSO {
	private Long userId;
	private int orderId;
	private String transactionId;
	private String status;
	public PaymentSuccessfulSO(Long userId, int orderId, String transactionId,String status) {
		this.userId=userId;
		this.orderId=orderId;
		this.transactionId = transactionId;
		this.status=status;
	}
	public PaymentSuccessfulSO() {
		
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
