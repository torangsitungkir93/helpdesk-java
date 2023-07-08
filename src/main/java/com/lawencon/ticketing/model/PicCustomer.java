package com.lawencon.ticketing.model;

public class PicCustomer extends BaseModel{
	private User pic;
	private User customer;
	
	public User getPic() {
		return pic;
	}
	public void setPic(User pic) {
		this.pic = pic;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	
}
