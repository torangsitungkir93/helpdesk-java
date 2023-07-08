package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.CommentAttachDao;
import com.lawencon.ticketing.model.CommentAttach;


public class CommentAttachDaoHQLImpl implements CommentAttachDao{
	private final EntityManager em;
	
	public CommentAttachDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public CommentAttach insert(CommentAttach commentAttach) throws SQLException {
		em.persist(commentAttach);
		return commentAttach;
	}

	@Override
	public List<CommentAttach> getAllByComment(Long commentId) throws SQLException {
		final String sql = "SELECT tca "
				+ "FROM CommentAttach tca "
				+ "WHERE tca.comment.id = :id";

		final List<CommentAttach> result = this.em.createQuery(sql, CommentAttach.class)
				.setParameter("id", commentId)
				.getResultList();
		return result;
	}


}
