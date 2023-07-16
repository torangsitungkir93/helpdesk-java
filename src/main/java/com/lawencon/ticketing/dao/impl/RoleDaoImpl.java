package com.lawencon.ticketing.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.model.Role;


@Repository
@Profile("native")
public class RoleDaoImpl implements RoleDao{
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll(){
		final String sql = "SELECT * FROM t_role "
				+ "WHERE code_role <> 'ADM' ";
		final List<Role> roles = this.em.createNativeQuery(sql,Role.class).getResultList();
		return roles;
	}

	@Override
	public Role getById(Long id){
		final Role role = this.em.find(Role.class, id);
		return role;
	}
}
