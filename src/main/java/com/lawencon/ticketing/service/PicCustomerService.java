package com.lawencon.ticketing.service;

import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.piccustomer.PicCustReqDto;

public interface PicCustomerService {
	InsertResDto insert(PicCustReqDto dto);
}
