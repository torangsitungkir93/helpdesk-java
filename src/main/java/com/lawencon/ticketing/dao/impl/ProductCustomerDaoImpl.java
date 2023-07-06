package com.lawencon.ticketing.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.ProductCustomerDao;
import com.lawencon.ticketing.model.ProductCustomer;

public class ProductCustomerDaoImpl implements ProductCustomerDao{
	private final EntityManager em;
	
	public ProductCustomerDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCustomer> getAll() throws SQLException {
		final String sql = "SELECT * FROM t_product_cust";
		
		final List<ProductCustomer> productCusts = this.em.createNativeQuery(sql,ProductCustomer.class).getResultList();
		return productCusts;
	}

	@Override
	public ProductCustomer insert(ProductCustomer productCustomer) throws SQLException {
		em.persist(productCustomer);
		return productCustomer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCustomer> getAllByIdCust(Long idCust) throws SQLException {
		final String sql = "SELECT * FROM t_products_cust pc "
				+ "INNER JOIN t_product p ON p.id = pc.product_id "
				+ "WHERE pc.customer_id = :idCust";
		final List<ProductCustomer> productCustomer = this.em.createNativeQuery(sql,ProductCustomer.class)
				.setParameter("idCust",idCust)
				.getResultList();
		return productCustomer;
	}

}
