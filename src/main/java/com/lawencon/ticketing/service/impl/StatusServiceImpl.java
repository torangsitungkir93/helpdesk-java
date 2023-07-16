package com.lawencon.ticketing.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.constant.RoleConst;
import com.lawencon.ticketing.constant.StatusConst;
import com.lawencon.ticketing.dao.RoleDao;
import com.lawencon.ticketing.dao.StatusDao;
import com.lawencon.ticketing.model.Status;
import com.lawencon.ticketing.service.StatusService;


@Service
public class StatusServiceImpl implements StatusService{
	private final StatusDao statusDao;
	@PersistenceContext
	private EntityManager em;
	
	public StatusServiceImpl(RoleDao roleDao, StatusDao statusDao,DataSource dataSource,SessionFactory factory) {
		this.statusDao = statusDao;
	}

	@Override
	public Status getByRoleAndStatus(String roleCode, String statusCode) {
		Status status = null;
		if(roleCode !=null && statusCode!=null) {
			if(roleCode.equals(RoleConst.CUST.getRoleCode())
					&&(statusCode.equals(StatusConst.OPEN.getStatusCode())
							||statusCode.equals(StatusConst.REOPEN.getStatusCode()))) {
				status = statusDao.getStatusByCode(StatusConst.CLOSED.getStatusCode());
			}else if (roleCode.equals(RoleConst.CUST.getRoleCode())
					&& statusCode.equals(StatusConst.PENDINGCUST.getStatusCode())){
				status = statusDao.getStatusByCode(StatusConst.CLOSED.getStatusCode());
			}else if (roleCode.equals(RoleConst.CUST.getRoleCode())
					&& statusCode.equals(StatusConst.CLOSED.getStatusCode())){
				status = statusDao.getStatusByCode(StatusConst.REOPEN.getStatusCode());
			}else if (roleCode.equals(RoleConst.PIC.getRoleCode())
					&& statusCode.equals(StatusConst.OPEN.getStatusCode())){
				status = statusDao.getStatusByCode(StatusConst.PENDINGAGENT.getStatusCode());
			}else if (roleCode.equals(RoleConst.PIC.getRoleCode())
					&& statusCode.equals(StatusConst.REOPEN.getStatusCode())){
				status = statusDao.getStatusByCode(StatusConst.PENDINGAGENT.getStatusCode());
			}else if (roleCode.equals(RoleConst.DEVELOPER.getRoleCode())
					&& statusCode.equals(StatusConst.PENDINGAGENT.getStatusCode())){
				status = statusDao.getStatusByCode(StatusConst.ONPROGGRESS.getStatusCode());
			}else if (roleCode.equals(RoleConst.DEVELOPER.getRoleCode())
					&& statusCode.equals(StatusConst.ONPROGGRESS.getStatusCode())){
				status = statusDao.getStatusByCode(StatusConst.PENDINGCUST.getStatusCode());
			}else {
				status= statusDao.getStatusByCode(statusCode);
			}
		}else {
			status = statusDao.getStatusByCode(StatusConst.OPEN.getStatusCode());  
		}
		return status;
	}

	@Override
	public Status getByIdRef(Long id) {
		final Status status = statusDao.getByIdRef(id);
		return status;
	}
}
