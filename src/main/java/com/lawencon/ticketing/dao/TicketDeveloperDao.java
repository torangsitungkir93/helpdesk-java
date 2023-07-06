package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.TicketDeveloper;

public interface TicketDeveloperDao {
	List<TicketDeveloper> getAll() throws SQLException;
	TicketDeveloper insert(TicketDeveloper ticketDeveloper) throws SQLException;
}
