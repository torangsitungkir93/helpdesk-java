package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.ProductDao;
import com.lawencon.ticketing.model.Company;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.Product;
import com.lawencon.ticketing.model.Profile;
import com.lawencon.ticketing.model.Role;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private final ProductDao productDao;
	private final EntityManager em;
	
	public ProductServiceImpl(ProductDao productDao,DataSource dataSource,SessionFactory factory) throws SQLException {
		this.productDao = productDao;
		this.em = EntityManagerConfig.get(factory);
	}

	@Override
	public List<Product> getAll() throws Exception {
		final List<Product> products = productDao.getAll();
		return products;
	}

	@Override
	public Product insertProduct(String productCode, String productName, Long createdById) throws SQLException {
		Product product = new Product();
		try {
			this.em.getTransaction().begin();
			product.setProductCode(productCode);
			product.setProductName(productName);
			product.setCreatedBy(createdById);

			final LocalDateTime timeNow = LocalDateTime.now();
			product.setCreatedAt(timeNow);
			product.setIsActive(true);
			product.setVer(0);

			productDao.insert(product);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return product;
	}

	@Override
	public Product updateProduct(Long idProduct,String productName,Long createdBy) throws SQLException {
		Product updatedProduct = new Product();
		try {
			this.em.getTransaction().begin();
			updatedProduct = productDao.getById(idProduct);
			updatedProduct.setProductName(productName);
			productDao.update(updatedProduct);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return updatedProduct;
	}
}
