package com.lawencon.ticketing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.user.UserInsertReqDto;
import com.lawencon.ticketing.dto.user.UserResDto;
import com.lawencon.ticketing.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/filter/")
	public ResponseEntity<List<UserResDto>> getAll(@RequestParam(value ="rolecode",required=false) String roleCode){
		final List<UserResDto> data = userService.getAll(roleCode);
		return new ResponseEntity <> (data,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody UserInsertReqDto data){
		final InsertResDto response = userService.insert(data);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
