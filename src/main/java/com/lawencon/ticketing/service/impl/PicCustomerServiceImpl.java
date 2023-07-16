package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.PicCustomerDao;
import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.dto.InsertResDto;
import com.lawencon.ticketing.dto.piccustomer.PicCustReqDto;
import com.lawencon.ticketing.model.PicCustomer;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.PicCustomerService;

@Service
public class PicCustomerServiceImpl implements PicCustomerService {
	private final static Long USER_ID = (long) 1;
	private PicCustomerDao picCustomerDao;
	private UserDao userDao;

	private PicCustomer picCustomer = null;
	private User userPic = null;
	private User userCustomer = null;
	@PersistenceContext
	private EntityManager em;

	public PicCustomerServiceImpl(PicCustomerDao picCustomerDao, UserDao userDao) throws SQLException {
		this.picCustomerDao = picCustomerDao;
		this.userDao = userDao;
	}

	@Transactional
	@Override
	public InsertResDto insert(PicCustReqDto dto) {

		InsertResDto response = null;
		PicCustomer picCustResult = null;
		userPic = userDao.getById(dto.getPicId());
		userCustomer = userDao.getById(dto.getCustomerId());

		picCustomer = new PicCustomer();
		picCustomer.setPic(userPic);
		picCustomer.setCustomer(userCustomer);
		picCustomer.setCreatedBy(USER_ID);

		final LocalDateTime timeNow = LocalDateTime.now();
		picCustomer.setCreatedAt(timeNow);
		picCustomer.setIsActive(true);
		picCustomer.setVer(0);

		picCustResult = picCustomerDao.insert(picCustomer);
		response = new InsertResDto();
		response.setId(picCustResult.getId());
		response.setMessage("Berhasil Input User");

		return response;
	}

}
