package com.lawencon.ticketing.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	private final RoleDao roleDao;
	@PersistenceContext
	private EntityManager em;
	
	public RoleServiceImpl(RoleDao roleDao,DataSource dataSource,SessionFactory factory) {
		this.roleDao = roleDao;
	}
	
	@Override
	public List<Role> getAll() {
		final List<Role> roles = roleDao.getAll();
		return roles;
	}
}
