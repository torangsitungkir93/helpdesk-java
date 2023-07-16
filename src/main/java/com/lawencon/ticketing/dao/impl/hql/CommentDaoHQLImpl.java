package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.CommentDao;
import com.lawencon.ticketing.model.Comment;


@Repository
@Profile("hql-query")
public class CommentDaoHQLImpl implements CommentDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Comment insert(Comment comment){
		 em.persist(comment);
		 return comment;
	}

	@Override
	public List<Comment> getAllByTicket(Long commentId){
		final String sql = "SELECT tc "
		        + "FROM Comment tc "
		        + "LEFT JOIN File tf ON tf.id = tc.user.profile.profilePhoto.id "
		        + "WHERE tc.ticket.id = :id "
		        + "ORDER BY tc.createdAt DESC";

		final List<Comment> result = this.em.createQuery(sql, Comment.class).setParameter("id", commentId).getResultList();
		return result;
	}
}
