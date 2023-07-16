package com.lawencon.ticketing.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.CommentAttach;

public interface CommentAttachDao {
	CommentAttach insert(CommentAttach commentAttach) ;
	List<CommentAttach> getAllByComment (Long commentId) ;
}
