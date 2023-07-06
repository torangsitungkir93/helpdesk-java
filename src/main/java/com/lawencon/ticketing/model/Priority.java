package com.lawencon.ticketing.model;

public class Priority extends BaseModel{
	private String codePriority;
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
