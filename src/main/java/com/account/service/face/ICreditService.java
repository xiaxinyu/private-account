package com.account.service.face;

import com.account.persist.model.Credit;
import com.account.persist.model.Page;
import com.account.service.exception.AppServiceException;

import java.util.List;

/**
 * Created by Summer.Xia on 09/01/2015.
 */
public interface ICreditService {
	void updateCredit(Credit credit, String userName) throws AppServiceException;
	
	void deleteCredit(String id) throws AppServiceException;
	
	public List<Credit> getCredits(Credit credit,Page page) throws AppServiceException;
	
	int countCredit(Credit credit) throws AppServiceException;
	
	void addCredits(List<String[]> rowDatas,String customerName,String recordID);

	void addCredits(List<Credit> credits, String userName);
	
	String consumeReport(Credit credit) throws AppServiceException;
	
	String weekConsumeReport(Credit credit) throws AppServiceException;
	
	String monthConsumeReport(Credit credit) throws AppServiceException;
}
