package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Role;

public interface RoleDao {
	List<Role> getAll() ;
	Role getById(Long id) ;
}
