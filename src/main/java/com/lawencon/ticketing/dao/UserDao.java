package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.User;

public interface UserDao {
	List<User> getAll() throws SQLException;
	List<User> getByRoleCode(String roleCode) throws SQLException;
	User getById(Long userId) throws SQLException;
	User insert(User user) throws SQLException;
	User update(User user) throws SQLException;
	User getByUserNameAndPassword(String email, String userPassword) throws SQLException;
}
