package com.lawencon.ticketing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_profile")
public class Profile extends BaseModel{
	
	@Column(name = "full_name",length =30,nullable = false)
	private String fullName;
	@Column(name = "phone",length =15,nullable = false)
	private String phone;
	@ManyToOne
	@JoinColumn(name = "photo_id")
	private File profilePhoto;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public File getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(File profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
