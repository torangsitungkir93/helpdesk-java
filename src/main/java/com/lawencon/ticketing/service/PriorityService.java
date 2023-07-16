package com.lawencon.ticketing.service;

import java.util.List;

import com.lawencon.ticketing.model.Priority;

public interface PriorityService {
	List<Priority> getAll() ;
	Priority getByIdRef(Long id) ;
}
