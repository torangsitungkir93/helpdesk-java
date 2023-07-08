package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.CommentDao;
import com.lawencon.ticketing.model.Comment;

public class CommentDaoHQLImpl implements CommentDao{
	private final EntityManager em;
	
	public CommentDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Comment insert(Comment comment) throws SQLException {
		 em.persist(comment);
		 return comment;
	}

	@Override
	public List<Comment> getAllByTicket(Long commentId) throws SQLException {
		final String sql = "SELECT tc "
		        + "FROM Comment tc "
		        + "LEFT JOIN File tf ON tf.id = tc.user.profile.profilePhoto.id "
		        + "WHERE tc.ticket.id = :id "
		        + "ORDER BY tc.createdAt DESC";

		final List<Comment> result = this.em.createQuery(sql, Comment.class).setParameter("id", commentId).getResultList();
		return result;
	}
}
