package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.PicCustomerDao;
import com.lawencon.ticketing.model.PicCustomer;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.PicCustomerService;

public class PicCustomerServiceImpl implements PicCustomerService{
	private PicCustomerDao picCustomerDao;

	private PicCustomer picCustomer = null;
	private User userPic = null;
	private User userCustomer = null;
	private final EntityManager em;

	public PicCustomerServiceImpl(PicCustomerDao picCustomerDao,DataSource dataSource,SessionFactory factory)throws SQLException {
		this.picCustomerDao = picCustomerDao;
		this.em = EntityManagerConfig.get(factory);
	}

	@Override
	public PicCustomer insert(Long picId, Long customerId, Long createdById) throws SQLException {
		try {
			this.em.getTransaction().begin();
			userPic = new User();
			userPic.setId(picId);
			
			userCustomer = new User();
			userCustomer.setId(customerId);
			
			picCustomer = new PicCustomer();
			picCustomer.setPic(userPic);
			picCustomer.setCustomer(userCustomer);
			picCustomer.setCreatedBy(createdById);

			final LocalDateTime timeNow = LocalDateTime.now();
			picCustomer.setCreatedAt(timeNow);
			picCustomer.setIsActive(true);
			picCustomer.setVer(0);

			picCustomerDao.insert(picCustomer);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return picCustomer;
	}

}
