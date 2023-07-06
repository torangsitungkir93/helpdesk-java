package com.lawencon.ticketing.constant;

public enum StatusConst {
	OPEN("OPEN", "Open"),
	PENDINGAGENT("PEAG", "Pending Agent"),
	ONPROGGRESS("ONPR", "On Progress"),
	PENDINGCUST("PNCU", "Pending Customer"),
	CLOSED("CLSD", "Closed"),
	REOPEN("ROPN", "Re-Open");
	
	private final String code;
	private final String name;

	StatusConst(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getStatusCode() {
		return this.code;
	}
}
