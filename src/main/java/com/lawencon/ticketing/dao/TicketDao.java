package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Ticket;

public interface TicketDao {
	List<Ticket> getAll() throws SQLException;
	List<Ticket> getAllByIdCust(Long idCust) throws SQLException;
	List<Ticket> getAllByIdPic(Long idPic,String statusCode,String statusCode2) throws SQLException;
	List<Ticket> getAllByIdDev(Long idDev,String statusCode,String statusCode2) throws SQLException;
	Ticket getTicketById(Long idTicket) throws SQLException;
	Ticket insert (Ticket ticket) throws SQLException;
	Ticket update (Ticket ticket) throws SQLException;
}
