package com.lawencon.ticketing.model;

public class ProductCustomer extends BaseModel{
	private Product product;
	private User customer;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
}
