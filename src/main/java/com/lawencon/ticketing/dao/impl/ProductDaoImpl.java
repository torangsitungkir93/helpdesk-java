package com.lawencon.ticketing.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.ProductDao;
import com.lawencon.ticketing.model.Product;
import com.lawencon.ticketing.model.ProductCustomer;
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
//	    final String sql = "UPDATE t_product SET product_name = ?, updated_by = ?, updated_at = ?, is_active = ?, ver = ver + 1 WHERE id = ? RETURNING *";
//	    final PreparedStatement ps = conn.prepareStatement(sql);
//	    ps.setString(1, product.getProductName());
//	    ps.setLong(2, product.getUpdatedBy());
//	    ps.setTimestamp(3, Timestamp.valueOf(product.getUpdatedAt()));
//	    ps.setBoolean(4, product.getIsActive());
//	    ps.setLong(5, product.getId());   
//	    final ResultSet rs = ps.executeQuery();
//	    
//	    if (rs.next()) {    
//	    	product.setVer(rs.getInt("ver"));
//	    }
	    return null;
	}
	
}
