package com.account.web.rest.model;

import java.util.List;

/**
 * Created by Summer.Xia on 2015/10/8.
 */
public class CollectionResult<T> {
	private int total;
	private List<T> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
