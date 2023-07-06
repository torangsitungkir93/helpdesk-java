package com.lawencon.ticketing.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.service.RoleService;

public class RoleServiceImpl implements RoleService{
	private final RoleDao roleDao;
	private final Connection conn;
	
	public RoleServiceImpl(RoleDao roleDao,DataSource dataSource) throws SQLException {
		this.roleDao = roleDao;
		this.conn = dataSource.getConnection();
		this.conn.setAutoCommit(false);
	}
	
	@Override
	public List<Role> getAll() throws SQLException {
		final List<Role> roles = roleDao.getAll();
		return roles;
	}
}
