package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.model.Role;

public class RoleDaoHQLImpl implements RoleDao{
	private final EntityManager em;
	
	public RoleDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<Role> getAll() throws SQLException {
		final String sql = "SELECT r FROM Role r";
		final List<Role> roles = this.em.createQuery(sql,Role.class).getResultList();
		return roles;
	}

	@Override
	public Role getById(Long id) throws SQLException {
		final Role role = this.em.find(Role.class, id);
		return role;
	}
}
