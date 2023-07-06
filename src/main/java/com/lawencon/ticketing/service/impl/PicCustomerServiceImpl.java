package com.lawencon.ticketing.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import com.lawencon.ticketing.dao.PicCustomerDao;
import com.lawencon.ticketing.model.PicCustomer;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.PicCustomerService;

public class PicCustomerServiceImpl implements PicCustomerService{
	private PicCustomerDao picCustomerDao;

	private PicCustomer picCustomer = null;
	private User userPic = null;
	private User userCustomer = null;
	private final Connection conn;

	public PicCustomerServiceImpl(PicCustomerDao picCustomerDao,DataSource dataSource)throws SQLException {
		this.picCustomerDao = picCustomerDao;
		this.conn = dataSource.getConnection();
		this.conn.setAutoCommit(false);
	}

	@Override
	public PicCustomer insert(Long picId, Long customerId, Long createdById) throws SQLException {
		try {
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
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return picCustomer;
	}

}
