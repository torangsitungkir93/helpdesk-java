package com.lawencon.ticketing.service;

import java.sql.SQLException;

import com.lawencon.ticketing.model.Status;

public interface StatusService {
	Status getByRoleAndStatus(String roleCode,String statusCode) throws SQLException;;
}
