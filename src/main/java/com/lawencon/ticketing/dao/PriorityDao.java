package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Priority;

public interface PriorityDao {
	List<Priority> getAll() throws SQLException;
}
