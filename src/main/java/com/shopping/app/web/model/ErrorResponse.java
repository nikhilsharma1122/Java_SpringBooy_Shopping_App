package com.shopping.app.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorResponse {
	private String description;
	private long orderId;
	public ErrorResponse(String description) {
		this.description = description;
	}
	public ErrorResponse(long orderId,String description) {
		this.description = description;
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
}
