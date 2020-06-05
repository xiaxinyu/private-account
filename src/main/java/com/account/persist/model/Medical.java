package com.account.persist.model;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by Summer.Xia on 10/13/2015.
 */
@TableName("medical")
public class Medical extends Base {
	private static final long serialVersionUID = 1L;
	private String unitNo;
	private String unitName;
	private String time;
	private Double payBase;
	private Double personalPay;
	private Double unitPay;
	private Double totalPay;
	private Double personalReserved;
	private String demoArea;
	
	private String year;

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getPayBase() {
		return payBase;
	}

	public void setPayBase(Double payBase) {
		this.payBase = payBase;
	}

	public Double getPersonalPay() {
		return personalPay;
	}

	public void setPersonalPay(Double personalPay) {
		this.personalPay = personalPay;
	}

	public Double getUnitPay() {
		return unitPay;
	}

	public void setUnitPay(Double unitPay) {
		this.unitPay = unitPay;
	}

	public Double getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(Double totalPay) {
		this.totalPay = totalPay;
	}

	public Double getPersonalReserved() {
		return personalReserved;
	}

	public void setPersonalReserved(Double personalReserved) {
		this.personalReserved = personalReserved;
	}

	public String getDemoArea() {
		return demoArea;
	}

	public void setDemoArea(String demoArea) {
		this.demoArea = demoArea;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
