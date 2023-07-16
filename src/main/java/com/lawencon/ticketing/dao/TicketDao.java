package com.lawencon.ticketing.dao;

import java.util.List;

import com.lawencon.ticketing.model.Ticket;

public interface TicketDao {
	List<Ticket> getAll() ;
	List<Ticket> getAllByIdCust(Long idCust) ;
	List<Ticket> getAllByIdPic(Long idPic,String statusCode,String statusCode2) ;
	List<Ticket> getAllByIdDev(Long idDev,String statusCode,String statusCode2) ;
	Ticket getTicketById(Long idTicket) ;
	Ticket insert (Ticket ticket) ;
	Ticket update (Ticket ticket) ;
}
