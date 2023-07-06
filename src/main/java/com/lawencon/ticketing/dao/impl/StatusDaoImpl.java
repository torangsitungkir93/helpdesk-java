package com.lawencon.ticketing.dao.impl;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.StatusDao;
import com.lawencon.ticketing.model.Status;

public class StatusDaoImpl implements StatusDao{
	private final EntityManager em;
	
	public StatusDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Status getStatusByCode(String code) throws SQLException {
	    final String sql = "SELECT s.id AS status_id, s.status_name AS status, s.status_code AS code FROM t_status s WHERE status_code = :code";
	    final Object statusObj = this.em.createNativeQuery(sql)
	            .setParameter("code", code)
	            .getSingleResult();

		final Object[] statusArr = (Object[]) statusObj;

		Status status = null;

		if (statusArr.length > 0) {
			status = new Status();
			status.setId(Long.valueOf(statusArr[0].toString()));
			status.setStatusName(statusArr[1].toString());
			status.setStatusCode(statusArr[2].toString());
		}
		return status;
	}

}
