package com.lawencon.ticketing.service;

import java.util.List;

import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.productcustomer.ProductCustReqDto;
import com.lawencon.ticketing.dto.productcustomer.ProductCustResDto;

public interface ProductCustomerService {
	List<ProductCustResDto> getAll(Long idCust);
	InsertResDto insert(ProductCustReqDto dto) ;
}
