package com.lawencon.ticketing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.productcustomer.ProductCustReqDto;
import com.lawencon.ticketing.dto.productcustomer.ProductCustResDto;
import com.lawencon.ticketing.service.ProductCustomerService;

@RestController
@RequestMapping("products-cust")
public class ProductCustController {
	private final ProductCustomerService productService;
	
	public ProductCustController(ProductCustomerService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/filter/")
	public ResponseEntity<List<ProductCustResDto>> getAll(@RequestParam(value ="q",required=false) Long idCust){
		final List<ProductCustResDto> data = productService.getAll(idCust);
		return new ResponseEntity <> (data,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody ProductCustReqDto data){
		final InsertResDto response = productService.insert(data);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
