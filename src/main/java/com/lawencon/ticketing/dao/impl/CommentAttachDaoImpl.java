package com.lawencon.ticketing.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.CommentAttachDao;
import com.lawencon.ticketing.model.CommentAttach;

@Repository
@Profile("native")
public class CommentAttachDaoImpl implements CommentAttachDao{
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public CommentAttach insert(CommentAttach commentAttach){
		em.persist(commentAttach);
		return commentAttach;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommentAttach> getAllByComment(Long commentId){
		final String sql = "SELECT * "
				+ "FROM t_comment_attach tca  "
				+ "INNER JOIN t_comment tc ON tca.comment_id = tc.id "
				+ "INNER JOIN t_file tf ON tca.file_id = tf.id  "
				+ "WHERE tc.id = :id";

		final List<CommentAttach> result = this.em.createNativeQuery(sql, CommentAttach.class).setParameter("id", commentId).getResultList();
		return result;
	}


}
