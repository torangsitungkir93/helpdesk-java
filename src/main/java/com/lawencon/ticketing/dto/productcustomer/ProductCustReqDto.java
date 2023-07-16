package com.lawencon.ticketing.dto.productcustomer;

public class ProductCustReqDto {
	private Long productId;
	private Long customerId;

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
}
