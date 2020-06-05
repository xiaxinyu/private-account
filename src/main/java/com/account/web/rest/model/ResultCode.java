package com.account.web.rest.model;

public enum ResultCode {
	OPERATION_FAILED("fail"), 
	OPERATION_SUCCEED("success");
	
	private String codeValue;

	ResultCode(String codeVal) {
		this.codeValue = codeVal;
	}

	public String getCodeValue() {
		return codeValue;
	}
}
