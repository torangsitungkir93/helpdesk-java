package com.lawencon.ticketing.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.Ticket;

public interface TicketService {
	Ticket createTicket(String ticketTitle,String ticketBody, Long userId,Long productId,Long ticketPriorityId,Long createdById,List<File> fileLists) throws SQLException;
	List<Ticket> getAllByIdCust(Long idCust) throws SQLException;
	List<Ticket> getAllByIdPic(Long idCust, String statusCode, String statusCode2) throws SQLException;
	List<Ticket> getAllByIdDev(Long idDev, String statusCode, String statusCode2) throws SQLException;
	Ticket updateStatusTicket(String roleCode,String statusCode,Ticket ticket,Long createdBy)throws SQLException;
	Ticket getTicketById(Long idTicket) throws SQLException;
}
