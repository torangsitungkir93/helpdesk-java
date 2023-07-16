package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Company;

public interface CompanyDao {
	List<Company> getAll() ;
	Company insert(Company company) ;
	Company update(Company company) ;
	Company getById(Long id) ;
}
