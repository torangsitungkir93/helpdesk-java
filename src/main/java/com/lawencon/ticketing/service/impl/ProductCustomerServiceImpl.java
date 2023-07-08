package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
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

	private final EntityManager em;

	public ProductCustomerServiceImpl(ProductCustomerDao productCustomerDao,DataSource dataSource,SessionFactory factory) throws SQLException{
		this.productCustomerDao = productCustomerDao;
		this.em = EntityManagerConfig.get(factory);
	}

	@Override
	public ProductCustomer insert(Long userId, Long productId, Long createdById) throws SQLException {
		try {
			this.em.getTransaction().begin();
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
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
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
