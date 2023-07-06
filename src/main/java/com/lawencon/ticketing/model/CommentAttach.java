package com.lawencon.ticketing.model;

public class CommentAttach extends BaseModel{
	
	private Comment comment;
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
