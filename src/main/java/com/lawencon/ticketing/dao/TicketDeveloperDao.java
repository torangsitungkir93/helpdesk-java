package com.lawencon.ticketing.dao;

import java.util.List;

import com.lawencon.ticketing.model.TicketDeveloper;

public interface TicketDeveloperDao {
	List<TicketDeveloper> getAll() ;
	TicketDeveloper insert(TicketDeveloper ticketDeveloper) ;
}
