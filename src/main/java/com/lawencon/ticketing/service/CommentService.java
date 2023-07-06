package com.lawencon.ticketing.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.ticketing.model.Comment;
import com.lawencon.ticketing.model.File;

public interface CommentService {
	Comment createComment(Long userId,Long ticketId, String message,Long createdById,List<File> fileLists) throws SQLException;
}
