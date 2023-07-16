package com.lawencon.ticketing.dao;

import java.sql.SQLException;

import com.lawencon.ticketing.model.FileTicket;


public interface FileTicketDao {
	FileTicket insert(FileTicket fileTicket);
}
