package com.account.web.rest;

import com.account.web.rest.model.CollectionResult;
import com.account.web.rest.model.CommonResult;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.core.StringTool;
import com.account.domain.model.Medical;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;
import com.account.application.IMedicalService;
import com.account.web.rest.model.MedicalParam;
import com.account.web.rest.model.ResultCode;

@Controller
@RequestMapping("/medical")
public class MedicalResource extends ControllerHelper {
	private static final Logger logger = LoggerFactory.getLogger(MedicalResource.class);
	
	@Autowired
	private IMedicalService medicalService;
	
	@RequestMapping("/getMedicals")
	@ResponseBody 
	public CollectionResult<Medical> getMedicals(MedicalParam param){
		try {
			//Fetch params
			Medical medical = new Medical();
			Page page = new Page(param.getPage(),param.getRows());
			CollectionResult<Medical> result = new CollectionResult<Medical>();
			result.setRows(medicalService.getMedicals(medical,page));
			result.setTotal(medicalService.countMedicals(medical));
			return result;
		} catch (AppServiceException e) {
			logger.error("get medicals failed. params[message = " + e.getMessage() + "]", e);
		} 
		return null;
	}
	
	@RequestMapping("/add")
	@ResponseBody 
	public String addMedical(Medical medical){
		try {
			String userName = this.getSessionUser().getUserName();
			medical.setId(StringTool.generateID());
			medical.setCreateuser(userName);
			medical.setUpdateuser(userName);
			medicalService.addMedical(medical);
			return JSONObject.toJSONString(new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功."));
		} catch (AppServiceException e) {
			logger.error("add medical failed. params[UnitNo = " + medical.getUnitNo() + ",Time = " + medical.getTime() + "]", e);
			return JSONObject.toJSONString(new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage()));
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody 
	public CommonResult deleteMedical(String id) {
		try {
			medicalService.deleteMedical(id);
			return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
		} catch (AppServiceException e) {
			logger.error("delete medical failed. params[id = " + id + "]", e);
			return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
		}
	}
	
	@RequestMapping("/update")
	@ResponseBody 
	public CommonResult updateMedical(Medical medical){
		try {
			String userName = this.getSessionUser().getUserName();
			medical.setUpdateuser(userName);
			medicalService.updateMedical(medical);
			return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
		} catch (AppServiceException e) {
			logger.error("update medical failed. params[id = " + medical.getId() + "]", e);
			return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
		}
	}
	
	@RequestMapping("/copy")
	@ResponseBody 
	public CommonResult copyMedical(Medical medical){
		try {
			String userName = this.getSessionUser().getUserName();
			medical.setId(StringTool.generateID());
			medical.setCreateuser(userName);
			medical.setUpdateuser(userName);
			medical.setUpdateuser(userName);
			medicalService.addMedical(medical);
			return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
		} catch (AppServiceException e) {
			logger.error("update medical failed. params[id = " + medical.getId() + "]", e);
			return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
		}
	}
}
