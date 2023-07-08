package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.ProductCustomerDao;
import com.lawencon.ticketing.model.ProductCustomer;

public class ProductCustomerDaoHQLImpl implements ProductCustomerDao{
	private final EntityManager em;
	
	public ProductCustomerDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<ProductCustomer> getAll() throws SQLException {
		final String sql = "SELECT pc FROM ProductCustomer";
		
		final List<ProductCustomer> productCusts = this.em.createQuery(sql,ProductCustomer.class).getResultList();
		return productCusts;
	}

	@Override
	public ProductCustomer insert(ProductCustomer productCustomer) throws SQLException {
		em.persist(productCustomer);
		return productCustomer;
	}

	@Override
	public List<ProductCustomer> getAllByIdCust(Long idCust) throws SQLException {
		final String sql = "SELECT pc "
				+ "FROM ProductCustomer pc "
				+ "WHERE pc.customer.id = :idCust";
		final List<ProductCustomer> productCustomer = this.em.createQuery(sql,ProductCustomer.class)
				.setParameter("idCust",idCust)
				.getResultList();
		return productCustomer;
	}

}
