package com.account.service.face;

import java.util.List;

import com.account.persist.model.Endowment;
import com.account.persist.model.Page;
import com.account.service.exception.AppServiceException;

/**
 * Created by Summer.Xia on 10/8/2015.
 */
public interface IEndowmentService {
	void addEndowment(Endowment endowment) throws AppServiceException;
	
	void updateEndowment(Endowment endowment) throws AppServiceException;
	
	void deleteEndowment(String id) throws AppServiceException;
	
	int countEndowments(Endowment endowment) throws AppServiceException;
	
	List<Endowment> getEndowments(Endowment endowment,Page page) throws AppServiceException;
}
