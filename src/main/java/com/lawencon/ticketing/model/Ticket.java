package com.lawencon.ticketing.model;

public class Ticket extends BaseModel{
	private User user;
	private Priority priority;
	private Status status;
	private String ticketCode;
	private String ticketTitle;
	private String ticketBody;
	private Product product;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getTicketTitle() {
		return ticketTitle;
	}
	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}
	public String getTicketBody() {
		return ticketBody;
	}
	public void setTicketBody(String ticketBody) {
		this.ticketBody = ticketBody;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
