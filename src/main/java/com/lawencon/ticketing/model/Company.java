package com.lawencon.ticketing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_company")
public class Company extends BaseModel{
	@Column(name = "name_company",length =30,nullable = false)
	private String nameCompany;
	@Column(name = "company_address",nullable = false)
	private String companyAddress;
	@Column(name = "company_code",unique=true,length =10,nullable = false)
	private String companyCode;
	@ManyToOne
	@JoinColumn(name = "photo_id")
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
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
}
