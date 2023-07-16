package com.lawencon.ticketing.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.ProductCustomerDao;
import com.lawencon.ticketing.model.ProductCustomer;


@Repository
@Profile("native")
public class ProductCustomerDaoImpl implements ProductCustomerDao{
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCustomer> getAll() {
		final String sql = "SELECT * FROM t_product_cust";
		
		final List<ProductCustomer> productCusts = this.em.createNativeQuery(sql,ProductCustomer.class).getResultList();
		return productCusts;
	}

	@Override
	public ProductCustomer insert(ProductCustomer productCustomer)  {
		em.persist(productCustomer);
		return productCustomer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCustomer> getAllByIdCust(Long idCust){
		final String sql = "SELECT * FROM t_products_cust pc "
				+ "INNER JOIN t_product p ON p.id = pc.product_id "
				+ "WHERE pc.customer_id = :idCust";
		final List<ProductCustomer> productCustomer = this.em.createNativeQuery(sql,ProductCustomer.class)
				.setParameter("idCust",idCust)
				.getResultList();
		return productCustomer;
	}

}
