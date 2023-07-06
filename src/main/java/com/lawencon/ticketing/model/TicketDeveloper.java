package com.lawencon.ticketing.model;

public class TicketDeveloper extends BaseModel{
	private Ticket ticket;
	private User developer;
	
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public User getDeveloper() {
		return developer;
	}
	public void setDeveloper(User developer) {
		this.developer = developer;
	}
	
}
