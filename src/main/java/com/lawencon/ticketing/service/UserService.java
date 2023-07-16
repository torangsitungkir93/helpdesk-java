package com.lawencon.ticketing.service;

import java.util.List;

import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.user.UserInsertReqDto;
import com.lawencon.ticketing.dto.user.UserResDto;
import com.lawencon.ticketing.dto.user.UserUpdatePhotoReqDto;
import com.lawencon.ticketing.dto.user.UserUpdatePhotoResDto;
import com.lawencon.ticketing.model.User;

public interface UserService {
//	List<User> getAll() ;
	List<UserResDto> getAll(String roleCode) ;
	InsertResDto insert(UserInsertReqDto data) ;
	User getUserById(Long id) ;
	UserUpdatePhotoResDto changePhoto(UserUpdatePhotoReqDto data) ;
	User login(String userName, String userPassword) ;
}
