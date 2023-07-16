package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.ProductDao;
import com.lawencon.ticketing.model.Product;

@Repository
@Profile("hql-query")
public class ProductDaoHQLImpl implements ProductDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Product> getAll(){
		final String sql = "SELECT tp FROM Product tp";
		final List<Product> products = this.em.createQuery(sql,Product.class).getResultList();
		return products;
	}

	@Override
	public Product insert(Product product){
		em.persist(product);
		return product;
	}

	@Override
	public Product update(Product product){
		return product;
	}

	@Override
	public Product getById(Long id){
		final Product product = this.em.find(Product.class, id);
		return product;
	}
	
}
