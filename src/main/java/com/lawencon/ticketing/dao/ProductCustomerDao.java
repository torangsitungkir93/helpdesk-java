package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.ProductCustomer;

public interface ProductCustomerDao {
	List<ProductCustomer> getAll();
	List<ProductCustomer> getAllByIdCust(Long idCust);
	ProductCustomer insert(ProductCustomer productCustomer);
}
