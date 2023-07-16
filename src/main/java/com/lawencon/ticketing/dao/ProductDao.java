package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Product;

public interface ProductDao {
	List<Product> getAll() ;
	Product insert(Product product) ;
	Product update(Product product) ;
	Product getById(Long id) ;
}
