package com.lawencon.ticketing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_status")
public class Status extends BaseModel{
	@Column(name = "status_code",unique=true,length =5,nullable = false)
	private String statusCode;
	@Column(name = "status_name",length =30,nullable = false)
	private String statusName;

	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
