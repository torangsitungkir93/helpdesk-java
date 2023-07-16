package com.lawencon.ticketing.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.CommentDao;
import com.lawencon.ticketing.model.Comment;


@Repository
@Profile("native")
public class CommentDaoImpl implements CommentDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Comment insert(Comment comment) {
		 em.persist(comment);
		 return comment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getAllByTicket(Long commentId) {
		final String sql = "SELECT *  "
				+ "FROM t_comment tte  "
				+ "INNER JOIN t_users tu ON tte.user_id = tu.id  "
				+ "INNER JOIN t_profile tp ON tu.profile_id = tp.id  "
				+ "INNER JOIN t_role tr ON tu.role_id = tr.id  "
				+ "LEFT JOIN t_file tf ON tp.photo_id = tf.id  "
				+ "WHERE tte.ticket_id = :id "
				+ "ORDER BY tte.created_at DESC";
		
		final List<Comment> result = this.em.createNativeQuery(sql, Comment.class).setParameter("id", commentId).getResultList();
		return result;
	}
}
