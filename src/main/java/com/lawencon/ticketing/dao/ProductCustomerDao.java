package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.ProductCustomer;

public interface ProductCustomerDao {
	List<ProductCustomer> getAll() throws SQLException;
	List<ProductCustomer> getAllByIdCust(Long idCust) throws SQLException;
	ProductCustomer insert(ProductCustomer productCustomer) throws SQLException;
}
