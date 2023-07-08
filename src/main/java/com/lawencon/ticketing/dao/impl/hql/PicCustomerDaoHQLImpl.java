package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.PicCustomerDao;
import com.lawencon.ticketing.model.PicCustomer;

public class PicCustomerDaoHQLImpl implements PicCustomerDao {
	private final EntityManager em;
	
	public PicCustomerDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}

	@Override
	public PicCustomer insert(PicCustomer picCustomer) throws SQLException {
		em.persist(picCustomer);
		 return picCustomer;
	}
}