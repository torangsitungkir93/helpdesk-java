package com.lawencon.ticketing.dao;

import java.sql.SQLException;

import com.lawencon.ticketing.model.File;

public interface FileDao {
	File getById(Long id) ;
	File insert(File file) ;
	boolean deleteById(Long id) ;
}
