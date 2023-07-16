package com.lawencon.ticketing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "t_role")
public class Role extends BaseModel{
	
	@Column(name = "name_role",length =30,nullable = false)
	private String nameRole;
	@Column(name = "code_role",unique = true,length =5,nullable = false)
	private String codeRole;
	
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	public String getCodeRole() {
		return codeRole;
	}
	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}
	
	
}
