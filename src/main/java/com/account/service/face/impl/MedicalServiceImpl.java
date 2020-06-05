package com.account.service.face.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.core.tool.DataStructureTool;
import com.account.core.tool.StringTool;
import com.account.persist.mapper.MedicalMapper;
import com.account.persist.model.Medical;
import com.account.persist.model.Page;
import com.account.service.exception.AppServiceException;
import com.account.service.face.IMedicalService;

/**
 * Created by Summer.Xia on 10/13/2015.
 */
@Service
public class MedicalServiceImpl implements IMedicalService{
	@Autowired
	private MedicalMapper medicalMapper;
	
	public void addMedical(Medical medical) throws AppServiceException {
		try {
			medicalMapper.addMedical(medical);
		} catch (Exception e) {
			throw new AppServiceException(e);
		}
	}
	
	public void updateMedical(Medical medical) throws AppServiceException {
		try {
			medicalMapper.updateMedical(medical);
		} catch (Exception e) {
			throw new AppServiceException(e);
		}
	}

	public void deleteMedical(String id) throws AppServiceException {
		try {
			medicalMapper.deleteMedical(id);
		} catch (Exception e) {
			throw new AppServiceException(e);
		}
	}

	public int countMedicals(Medical medical) throws AppServiceException {
		int result = 0;
		try {
			result = medicalMapper.countMedicals(medical);
		} catch (Exception e) {
			throw new AppServiceException(e);
		}
		return result;
	}

	public List<Medical> getMedicals(Medical medical, Page page) throws AppServiceException {
		List<Medical> result = null;
		try {
			result = medicalMapper.getMedicals(medical,page);
			if(DataStructureTool.isNotEmpty(result)){
				for (Medical item : result) {
					String time = item.getTime();
					item.setYear(time);
					if(!StringTool.isNullOrEmpty(time)){
						String[] args = time.split("-");
						if(args.length>=2){
							item.setYear(args[0]);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new AppServiceException(e);
		}
		return result;
	}
}
