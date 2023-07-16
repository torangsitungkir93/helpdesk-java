package com.lawencon.ticketing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.ticket.TicketInsertReqDto;
import com.lawencon.ticketing.service.TicketService;


@RestController
@RequestMapping("tickets")
public class TicketController {
	private final TicketService ticketService;
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
//	@GetMapping("/filter/")
//	public ResponseEntity<List<UserResDto>> getAll(@RequestParam(value ="rolecode",required=false) String roleCode){
//		final List<UserResDto> data = userService.getAll(roleCode);
//		return new ResponseEntity <> (data,HttpStatus.OK);
//	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody TicketInsertReqDto data){
		final InsertResDto response = ticketService.createTicket(data);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
