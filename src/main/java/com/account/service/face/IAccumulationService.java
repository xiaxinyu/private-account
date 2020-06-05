package com.account.service.face;

import java.util.List;

import com.account.persist.model.Accumulation;
import com.account.persist.model.Page;
import com.account.service.exception.AppServiceException;

/**
 * Created by Summer.Xia on 10/8/2015.
 */
public interface IAccumulationService {
	void addAccumulation(Accumulation accumulation) throws AppServiceException;
	
	void updateAccumulation(Accumulation accumulation) throws AppServiceException;
	
	void deleteAccumulation(String id) throws AppServiceException;
	
	int countAccumulations(Accumulation accumulation) throws AppServiceException;
	
	List<Accumulation> getAccumulations(Accumulation accumulation,Page page) throws AppServiceException;
}
