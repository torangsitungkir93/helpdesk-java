package com.lawencon.ticketing.dao;

import java.util.List;

import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.User;

public interface UserDao {
	List<User> getAll() ;
	List<User> getByRoleCode(String roleCode) ;
	User getById(Long userId) ;
	User insert(User user) ;
	Profile update(Profile profile) ;
	User getByUserNameAndPassword(String email, String userPassword) ;
}
