package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.CommentAttachDao;
import com.lawencon.ticketing.dao.CommentDao;
import com.lawencon.ticketing.dao.FileDao;
import com.lawencon.ticketing.dao.TicketDao;
import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.model.Comment;
import com.lawencon.ticketing.model.CommentAttach;
import com.lawencon.ticketing.model.File;
import com.lawencon.ticketing.model.Ticket;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.CommentService;

public class CommentServiceImpl implements CommentService {

	private final FileDao fileDao;
	private final CommentAttachDao commentAttachDao;
	private final CommentDao commentDao;
	private final UserDao userDao;
	private final EntityManager em;

	public CommentServiceImpl(TicketDao ticketDao, FileDao fileDao, CommentAttachDao commentAttachDao,
			CommentDao commentDao, DataSource dataSource,UserDao userDao,SessionFactory factory) throws SQLException {
		this.fileDao = fileDao;
		this.commentAttachDao = commentAttachDao;
		this.commentDao = commentDao;
		this.userDao = userDao;
		this.em = EntityManagerConfig.get(factory);
	}


	@Override
	public Comment createComment(Long userId, Long ticketId, String message, Long createdById, List<File> fileLists)
			throws SQLException {

		final Comment comment = new Comment();
		final Ticket ticket = new Ticket();
		final User user = userDao.getById(userId);

		try {
			this.em.getTransaction().begin();
			comment.setUser(user);

			ticket.setId(ticketId);
			comment.setTicket(ticket);

			comment.setMessage(message);

			comment.setCreatedBy(createdById);
			final LocalDateTime timeNow = LocalDateTime.now();
			comment.setCreatedAt(timeNow);
			comment.setIsActive(true);
			comment.setVer(0);

			commentDao.insert(comment);
			if (fileLists.size() > 0) {
				for (int i = 0; i < fileLists.size(); i++) {
					final CommentAttach commentAttach = new CommentAttach();
					final File file = fileDao.insert(fileLists.get(i));
					commentAttach.setComment(comment);
					commentAttach.setFile(file);
					commentAttach.setCreatedBy(createdById);
					commentAttach.setCreatedAt(timeNow);
					commentAttach.setIsActive(true);
					commentAttach.setVer(0);
					commentAttachDao.insert(commentAttach);
				}
			}
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return comment;
	}

	@Override
	public List<Comment> getAllByTicket(Long commentId) throws SQLException {
		final List<Comment> comments = commentDao.getAllByTicket(commentId);
		return comments;
	}

	@Override
	public List<CommentAttach> getAllByComment(Long commentId) throws SQLException {
		final List<CommentAttach> commentAttachs = commentAttachDao.getAllByComment(commentId);
		return commentAttachs;
	}

}
