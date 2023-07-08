package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Company;

public interface CompanyDao {
	List<Company> getAll() throws SQLException;
	Company insert(Company company) throws SQLException;
	Company update(Company company) throws SQLException;
	Company getById(Long id) throws SQLException;
}
