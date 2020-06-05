package com.account.web.rest.model;

/**
 * Created by Summer.Xia on 2015/9/17.
 */
public class HouseRentParam extends PageParam {
	private String transactionDateStartStr;
	private String transactionDateEndStr;
	private String consumptionType;
	private String cardTypeName;
	private String consumeName;
	private String consumeID;
	private String demoArea;
	private String weekName;
	private String year;
	private String month;

	public String getTransactionDateStartStr() {
		return transactionDateStartStr;
	}

	public void setTransactionDateStartStr(String transactionDateStartStr) {
		this.transactionDateStartStr = transactionDateStartStr;
	}

	public String getTransactionDateEndStr() {
		return transactionDateEndStr;
	}

	public void setTransactionDateEndStr(String transactionDateEndStr) {
		this.transactionDateEndStr = transactionDateEndStr;
	}

	public String getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(String consumptionType) {
		this.consumptionType = consumptionType;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public String getConsumeName() {
		return consumeName;
	}

	public void setConsumeName(String consumeName) {
		this.consumeName = consumeName;
	}

	public String getConsumeID() {
		return consumeID;
	}

	public void setConsumeID(String consumeID) {
		this.consumeID = consumeID;
	}

	public String getDemoArea() {
		return demoArea;
	}

	public void setDemoArea(String demoArea) {
		this.demoArea = demoArea;
	}

	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
