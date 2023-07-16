package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.ProductCustomerDao;
import com.lawencon.ticketing.model.ProductCustomer;


@Repository
@Profile("hql-query")
public class ProductCustomerDaoHQLImpl implements ProductCustomerDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ProductCustomer> getAll(){
		final String sql = "SELECT pc FROM ProductCustomer";
		
		final List<ProductCustomer> productCusts = this.em.createQuery(sql,ProductCustomer.class).getResultList();
		return productCusts;
	}

	@Override
	public ProductCustomer insert(ProductCustomer productCustomer){
		em.persist(productCustomer);
		return productCustomer;
	}

	@Override
	public List<ProductCustomer> getAllByIdCust(Long idCust){
		final String sql = "SELECT pc "
				+ "FROM ProductCustomer pc "
				+ "WHERE pc.customer.id = :idCust";
		final List<ProductCustomer> productCustomer = this.em.createQuery(sql,ProductCustomer.class)
				.setParameter("idCust",idCust)
				.getResultList();
		return productCustomer;
	}

}
