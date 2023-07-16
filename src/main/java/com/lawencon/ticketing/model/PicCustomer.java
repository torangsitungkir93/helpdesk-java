package com.lawencon.ticketing.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "t_pic_customer")
public class PicCustomer extends BaseModel{
	@ManyToOne
	@JoinColumn(name = "pic_id")
	private User pic;
	@ManyToOne
	@JoinColumn(name = "customer_id")
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
