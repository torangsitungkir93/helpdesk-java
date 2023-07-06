package com.lawencon.ticketing.service;

import java.sql.SQLException;

import com.lawencon.ticketing.model.PicCustomer;

public interface PicCustomerService {
	PicCustomer insert(Long picId,Long customerId,Long createdById) throws SQLException;
}
