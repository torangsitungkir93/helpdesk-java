package com.lawencon.ticketing.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Role;

public interface RoleService {
	List<Role> getAll() throws SQLException;
}
