package com.lawencon.ticketing.dao;

import java.sql.SQLException;

import com.lawencon.ticketing.model.PicCustomer;

public interface PicCustomerDao {
	PicCustomer insert(PicCustomer picCustomer) throws SQLException;
}
