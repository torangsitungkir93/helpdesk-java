package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.StatusDao;
import com.lawencon.ticketing.model.Status;

public class StatusDaoHQLImpl implements StatusDao{
	private final EntityManager em;
	
	public StatusDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Status getStatusByCode(String code) throws SQLException {
	    final String sql = "SELECT s.id AS status_id, s.statusName AS status, s.statusCode AS code "
	    		+ "FROM Status s "
	    		+ "WHERE s.statusCode = :code";
	    final Object statusObj = this.em.createQuery(sql)
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

	@Override
	public Status getByIdRef(Long id) throws SQLException {
		final Status status = this.em.getReference(Status.class, id);
		return status;
	}

}