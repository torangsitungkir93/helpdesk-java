package com.lawencon.ticketing.service;

import com.lawencon.ticketing.model.Status;

public interface StatusService {
	Status getByRoleAndStatus(String roleCode,String statusCode) ;
	Status getByIdRef(Long id) ;
}
