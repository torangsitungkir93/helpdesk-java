package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.model.Role;


@Repository
@Profile("hql-query")
public class RoleDaoHQLImpl implements RoleDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Role> getAll(){
		final String sql = "SELECT r FROM Role r";
		final List<Role> roles = this.em.createQuery(sql,Role.class).getResultList();
		return roles;
	}

	@Override
	public Role getById(Long id){
		final Role role = this.em.find(Role.class, id);
		return role;
	}
}
