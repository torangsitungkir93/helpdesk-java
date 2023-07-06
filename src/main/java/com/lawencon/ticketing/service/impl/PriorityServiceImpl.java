package com.lawencon.ticketing.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.lawencon.ticketing.dao.PriorityDao;
import com.lawencon.ticketing.model.Priority;
import com.lawencon.ticketing.service.PriorityService;

public class PriorityServiceImpl implements PriorityService{
	
	private final PriorityDao priorityDao;
	private final Connection conn;

	public PriorityServiceImpl(PriorityDao priorityDao,DataSource dataSource) throws SQLException {
		this.priorityDao = priorityDao;
		this.conn = dataSource.getConnection();
		this.conn.setAutoCommit(false);
	}

	@Override
	public List<Priority> getAll() throws SQLException {
		final List<Priority> priorities = priorityDao.getAll();
		return priorities;
	}

}
