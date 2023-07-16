package com.lawencon.ticketing.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.PicCustomerDao;
import com.lawencon.ticketing.model.PicCustomer;


@Repository
@Profile("native")
public class PicCustomerDaoImpl implements PicCustomerDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public PicCustomer insert(PicCustomer picCustomer) {
		em.persist(picCustomer);
		 return picCustomer;
	}
}
