package com.lawencon.ticketing.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import com.lawencon.ticketing.dao.ProductCustomerDao;
import com.lawencon.ticketing.model.Product;
import com.lawencon.ticketing.model.ProductCustomer;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.ProductCustomerService;

public class ProductCustomerServiceImpl implements ProductCustomerService {
	private ProductCustomerDao productCustomerDao;

	private ProductCustomer productCustomer = null;
	private User user = null;
	private Product product = null;

	private final Connection conn;

	public ProductCustomerServiceImpl(ProductCustomerDao productCustomerDao,DataSource dataSource) throws SQLException{
		this.productCustomerDao = productCustomerDao;
		this.conn = dataSource.getConnection();
		this.conn.setAutoCommit(false);
	}

	@Override
	public ProductCustomer insert(Long userId, Long productId, Long createdById) throws SQLException {
		try {
			user = new User();
			user.setId(userId);

			product = new Product();
			product.setId(productId);

			productCustomer = new ProductCustomer();
			productCustomer.setCustomer(user);
			productCustomer.setProduct(product);
			productCustomer.setCreatedBy(createdById);

			final LocalDateTime timeNow = LocalDateTime.now();
			productCustomer.setCreatedAt(timeNow);
			productCustomer.setIsActive(true);
			productCustomer.setVer(0);

			productCustomerDao.insert(productCustomer);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return productCustomer;
	}

	@Override
	public List<ProductCustomer> getAllByIdCust(Long idCust) throws SQLException {
		final List<ProductCustomer> products = productCustomerDao.getAllByIdCust(idCust);
		return products;
	}

}
