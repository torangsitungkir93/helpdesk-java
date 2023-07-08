package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.ProductDao;
import com.lawencon.ticketing.model.Product;
public class ProductDaoHQLImpl implements ProductDao {
	private final EntityManager em;
	
	public ProductDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<Product> getAll() throws SQLException {
		final String sql = "SELECT tp FROM Product tp";
		final List<Product> products = this.em.createQuery(sql,Product.class).getResultList();
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
