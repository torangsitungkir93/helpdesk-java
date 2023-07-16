package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.StatusDao;
import com.lawencon.ticketing.model.Status;


@Repository
@Profile("hql-query")
public class StatusDaoHQLImpl implements StatusDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Status getStatusByCode(String code){
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
	public Status getByIdRef(Long id){
		final Status status = this.em.getReference(Status.class, id);
		return status;
	}

}
