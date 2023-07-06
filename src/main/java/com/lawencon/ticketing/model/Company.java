package com.lawencon.ticketing.model;

public class Company extends BaseModel{
	private String nameCompany;
	private String companyAddress;
	private File photoId;
	
	
	public String getNameCompany() {
		return nameCompany;
	}
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public File getPhotoId() {
		return photoId;
	}
	public void setPhotoId(File photoId) {
		this.photoId = photoId;
	}
}
