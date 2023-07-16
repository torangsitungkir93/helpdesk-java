package com.lawencon.ticketing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.piccustomer.PicCustReqDto;
import com.lawencon.ticketing.service.PicCustomerService;

@RestController
@RequestMapping("piccusts")
public class PicCustomerController {
private final PicCustomerService picCustService;
	
	public PicCustomerController(PicCustomerService picCustService) {
		this.picCustService = picCustService;
	}
	
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody PicCustReqDto data){
		final InsertResDto response = picCustService.insert(data);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
