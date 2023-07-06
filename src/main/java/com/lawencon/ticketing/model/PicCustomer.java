package com.lawencon.ticketing.model;

public class PicCustomer extends BaseModel{
	private User pic;
	private User Customer;
	
	public User getPic() {
		return pic;
	}
	public void setPic(User pic) {
		this.pic = pic;
	}
	public User getCustomer() {
		return Customer;
	}
	public void setCustomer(User customer) {
		Customer = customer;
	}
}
