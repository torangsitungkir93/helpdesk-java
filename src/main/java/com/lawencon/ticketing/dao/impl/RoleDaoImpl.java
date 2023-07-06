package com.lawencon.ticketing.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.model.Role;

public class RoleDaoImpl implements RoleDao{
	private final EntityManager em;
	
	public RoleDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() throws SQLException {
		final String sql = "SELECT * FROM t_role";
		final List<Role> roles = this.em.createNativeQuery(sql,Role.class).getResultList();
		return roles;
	}
}
