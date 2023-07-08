package com.lawencon.ticketing.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.ProductDao;
import com.lawencon.ticketing.model.Product;
public class ProductDaoImpl implements ProductDao {
	private final EntityManager em;
	
	public ProductDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll() throws SQLException {
		final String sql = "SELECT * FROM t_product";
		final List<Product> products = this.em.createNativeQuery(sql,Product.class).getResultList();
		return products;
	}

	@Override
	public Product insert(Product product) throws SQLException {
		em.persist(product);
		return product;
	}

	@Override
	public Product update(Product product) throws SQLException {
		return product;
	}

	@Override
	public Product getById(Long id) throws SQLException {
		final Product product = this.em.find(Product.class, id);
		return product;
	}
	
}
