package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Comment;

public interface CommentDao {
	Comment insert (Comment comment) throws SQLException;
	List<Comment> getAllByTicket(Long commentId) throws SQLException;
}
