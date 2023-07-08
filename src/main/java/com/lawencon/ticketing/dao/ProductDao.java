package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Product;

public interface ProductDao {
	List<Product> getAll() throws SQLException;
	Product insert(Product product) throws SQLException;
	Product update(Product product) throws SQLException;
	Product getById(Long id) throws SQLException;
}
