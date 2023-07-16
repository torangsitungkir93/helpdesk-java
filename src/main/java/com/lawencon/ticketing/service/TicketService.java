package com.lawencon.ticketing.service;

import java.util.List;

import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.ticket.TicketInsertReqDto;
import com.lawencon.ticketing.model.Ticket;

public interface TicketService {
	InsertResDto createTicket(TicketInsertReqDto data);
	List<Ticket> getAllByIdCust(Long idCust) ;
	List<Ticket> getAllByIdPic(Long idCust, String statusCode, String statusCode2) ;
	List<Ticket> getAllByIdDev(Long idDev, String statusCode, String statusCode2) ;
	Ticket updateStatusTicket(String roleCode,String statusCode,Ticket ticket,Long createdBy);
	Ticket getTicketById(Long idTicket) ;
}
