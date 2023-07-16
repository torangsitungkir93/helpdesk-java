package com.lawencon.ticketing.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_comment_attach")
public class CommentAttach extends BaseModel{
	
	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;
	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	

	
}
