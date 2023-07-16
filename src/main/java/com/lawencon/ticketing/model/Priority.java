package com.lawencon.ticketing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "t_priority")
public class Priority extends BaseModel{
	
	@Column(name = "code_priority",unique=true,length =5,nullable = false)
	private String codePriority;
	@Column(name = "name_priority",length =25,nullable = false)
	private String namePriority;
	
	
	public String getNamePriority() {
		return namePriority;
	}
	public void setNamePriority(String namePriority) {
		this.namePriority = namePriority;
	}
	public String getCodePriority() {
		return codePriority;
	}
	public void setCodePriority(String codePriority) {
		this.codePriority = codePriority;
	}
	
	
}
