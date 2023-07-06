package com.lawencon.ticketing.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import com.lawencon.ticketing.dao.ProductDao;
import com.lawencon.ticketing.model.Product;
import com.lawencon.ticketing.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private final ProductDao productDao;
	private final Connection conn;
	
	public ProductServiceImpl(ProductDao productDao,DataSource dataSource) throws SQLException {
		this.productDao = productDao;
		this.conn = dataSource.getConnection();
		this.conn.setAutoCommit(false);
	}

	@Override
	public List<Product> getAll() throws Exception {
		final List<Product> products = productDao.getAll();
		return products;
	}

	@Override
	public Product insert(String productCode, String productName, Long createdById) throws SQLException {
		Product product = new Product();
		try {
			product.setProductCode(productCode);
			product.setProductName(productName);
			product.setCreatedBy(createdById);

			final LocalDateTime timeNow = LocalDateTime.now();
			product.setCreatedAt(timeNow);
			product.setIsActive(true);
			product.setVer(0);

			productDao.insert(product);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return product;
	}

	@Override
	public Product update(String productCode,Long createdBy) throws SQLException {
		Product updatedProduct = new Product();
		try {
			final LocalDateTime timeNow = LocalDateTime.now();
			updatedProduct.setUpdatedAt (timeNow);
			updatedProduct.setUpdatedBy(createdBy);
			productDao.update(updatedProduct);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace () ;
				try {
					conn.rollback() ;
				} catch (Exception el) {
					e.printStackTrace();
				}
		}
		return updatedProduct;
	}
	
}
