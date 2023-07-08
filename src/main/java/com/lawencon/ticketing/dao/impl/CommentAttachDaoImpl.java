package com.lawencon.ticketing.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.CommentAttachDao;
import com.lawencon.ticketing.model.CommentAttach;


public class CommentAttachDaoImpl implements CommentAttachDao{
	private final EntityManager em;
	
	public CommentAttachDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public CommentAttach insert(CommentAttach commentAttach) throws SQLException {
		em.persist(commentAttach);
		return commentAttach;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommentAttach> getAllByComment(Long commentId) throws SQLException {
		final String sql = "SELECT * "
				+ "FROM t_comment_attach tca  "
				+ "INNER JOIN t_comment tc ON tca.comment_id = tc.id "
				+ "INNER JOIN t_file tf ON tca.file_id = tf.id  "
				+ "WHERE tc.id = :id";

		final List<CommentAttach> result = this.em.createNativeQuery(sql, CommentAttach.class).setParameter("id", commentId).getResultList();
		return result;
	}


}
