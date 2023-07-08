package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.service.RoleService;

public class RoleServiceImpl implements RoleService{
	private final RoleDao roleDao;
	private final EntityManager em;
	
	public RoleServiceImpl(RoleDao roleDao,DataSource dataSource,SessionFactory factory) throws SQLException {
		this.roleDao = roleDao;
		this.em = EntityManagerConfig.get(factory);
	}
	
	@Override
	public List<Role> getAll() throws SQLException {
		final List<Role> roles = roleDao.getAll();
		return roles;
	}
}
