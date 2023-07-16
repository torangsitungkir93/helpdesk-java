package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.CommentAttachDao;
import com.lawencon.ticketing.model.CommentAttach;

@Repository
@Profile("hql-query")
public class CommentAttachDaoHQLImpl implements CommentAttachDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public CommentAttach insert(CommentAttach commentAttach) {
		em.persist(commentAttach);
		return commentAttach;
	}

	@Override
	public List<CommentAttach> getAllByComment(Long commentId) {
		final String sql = "SELECT tca "
				+ "FROM CommentAttach tca "
				+ "WHERE tca.comment.id = :id";

		final List<CommentAttach> result = this.em.createQuery(sql, CommentAttach.class)
				.setParameter("id", commentId)
				.getResultList();
		return result;
	}


}
