package com.lawencon.ticketing.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_ticket_developer")
public class TicketDeveloper extends BaseModel{
	
	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;
	@ManyToOne
	@JoinColumn(name = "developer_id")
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
