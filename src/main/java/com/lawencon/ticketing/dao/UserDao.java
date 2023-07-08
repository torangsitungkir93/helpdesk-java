package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.User;

public interface UserDao {
	List<User> getAll() throws SQLException;
	List<User> getByRoleCode(String roleCode) throws SQLException;
	User getById(Long userId) throws SQLException;
	User insert(User user) throws SQLException;
	Profile update(Profile profile) throws SQLException;
	User getByUserNameAndPassword(String email, String userPassword) throws SQLException;
}
