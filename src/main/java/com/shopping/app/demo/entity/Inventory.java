package com.shopping.app.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="inventory")
public class Inventory {
	@Id
	@Column(name="inventory_id")
	private long inventory_id;
	@Column(name="ordered")
	private int ordered;
	@Column(name="price")
	private int price;
	@Column(name="available")
	private int available;
	public long getInventory_id() {
		return inventory_id;
	}
	public void setInventory_id(long inventory_id) {
		this.inventory_id = inventory_id;
	}
	public int getOrdered() {
		return ordered;
	}
	public void setOrdered(int ordered) {
		this.ordered = ordered;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "Inventory [inventory_id=" + inventory_id + ", ordered=" + ordered + ", price=" + price + ", available="
				+ available + "]";
	}
	
}
