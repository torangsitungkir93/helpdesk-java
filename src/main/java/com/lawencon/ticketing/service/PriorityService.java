package com.lawencon.ticketing.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Priority;

public interface PriorityService {
	List<Priority> getAll() throws SQLException;
}
