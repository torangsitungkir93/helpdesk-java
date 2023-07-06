package com.lawencon.ticketing.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.ProductCustomer;

public interface ProductCustomerService {
	List<ProductCustomer> getAllByIdCust(Long idCust) throws SQLException;
	ProductCustomer insert(Long userId,Long productId,Long createdById) throws SQLException;
}
