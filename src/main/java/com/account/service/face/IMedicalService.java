package com.account.service.face;

import java.util.List;

import com.account.persist.model.Medical;
import com.account.persist.model.Page;
import com.account.service.exception.AppServiceException;

/**
 * Created by Summer.Xia on 10/8/2015.
 */
public interface IMedicalService {
	void addMedical(Medical medical) throws AppServiceException;
	
	void updateMedical(Medical medical) throws AppServiceException;
	
	void deleteMedical(String id) throws AppServiceException;
	
	int countMedicals(Medical medical) throws AppServiceException;
	
	List<Medical> getMedicals(Medical medical,Page page) throws AppServiceException;
}
