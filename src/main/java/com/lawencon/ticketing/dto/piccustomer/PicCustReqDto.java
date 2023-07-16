package com.lawencon.ticketing.dto.piccustomer;

public class PicCustReqDto {
	private Long picId;
	private Long customerId;

	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getPicId() {
		return picId;
	}
	public void setPicId(Long picId) {
		this.picId = picId;
	}
	
}
