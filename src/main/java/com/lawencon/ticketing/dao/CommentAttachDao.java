package com.lawencon.ticketing.dao;

import java.sql.SQLException;

import com.lawencon.ticketing.model.CommentAttach;

public interface CommentAttachDao {
	CommentAttach insert(CommentAttach commentAttach) throws SQLException;
}
