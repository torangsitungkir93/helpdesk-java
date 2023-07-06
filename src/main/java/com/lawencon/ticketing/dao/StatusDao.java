package com.lawencon.ticketing.dao;

import java.sql.SQLException;

import com.lawencon.ticketing.model.Status;

public interface StatusDao {
	Status getStatusByCode(String code) throws SQLException;
}
