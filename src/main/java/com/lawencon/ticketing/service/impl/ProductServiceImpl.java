package com.lawencon.ticketing.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.ProductDao;
import com.lawencon.ticketing.model.Product;
import com.lawencon.ticketing.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductDao productDao;
	@PersistenceContext
	private EntityManager em;
	
	public ProductServiceImpl(ProductDao productDao,DataSource dataSource,SessionFactory factory) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> getAll() {
		final List<Product> products = productDao.getAll();
		return products;
	}

	@Override
	public Product insertProduct(String productCode, String productName, Long createdById) {
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
	public Product updateProduct(Long idProduct,String productName,Long createdBy) {
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
