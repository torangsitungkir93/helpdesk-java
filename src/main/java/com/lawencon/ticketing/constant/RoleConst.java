package com.lawencon.ticketing.constant;

public enum RoleConst {
	SUPERADMIN("ADM", "Super Admin"),
	PIC("PIC", "PIC"),
	DEVELOPER("DEV", "Customer"),
	CUST("CST", "System");

	private final String code;
	private final String name;

	RoleConst(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getRoleCode() {
		return this.code;
	}
}
