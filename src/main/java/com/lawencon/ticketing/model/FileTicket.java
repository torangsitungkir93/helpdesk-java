package com.lawencon.ticketing.model;

public class FileTicket extends BaseModel{
	private Ticket ticket;
	private File file;
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
