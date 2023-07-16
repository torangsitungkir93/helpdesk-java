package com.lawencon.ticketing.service;

import com.lawencon.ticketing.model.TicketDeveloper;

public interface TicketDeveloperService {
	TicketDeveloper insert(Long ticketId,Long developerId,Long createdById);
}
