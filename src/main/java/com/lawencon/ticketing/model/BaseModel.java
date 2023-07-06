package com.lawencon.ticketing.model;

import java.time.LocalDateTime;

public class BaseModel {
	private Long id;
	private Long createdBy;
	private LocalDateTime createdAt;
	private Long updatedBy;
	private LocalDateTime updatedAt;
	private Boolean isActive;
	private Integer ver;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long l) {
		this.createdBy = l;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long i) {
		this.updatedBy = i;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updateAt) {
		this.updatedAt = updateAt;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
}
