package com.account.web.rest;

import com.account.web.rest.model.CommonResult;
import com.account.web.rest.model.EndowmentParam;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.core.StringTool;
import com.account.domain.model.Endowment;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;
import com.account.application.IEndowmentService;
import com.account.web.rest.model.CollectionResult;
import com.account.web.rest.model.ResultCode;

@Controller
@RequestMapping("/endowment")
public class EndowmentResource extends ControllerHelper {
	private static final Logger logger = LoggerFactory.getLogger(EndowmentResource.class);
	
	@Autowired
	private IEndowmentService endowmentService;
	
	@RequestMapping("/getEndowments")
	@ResponseBody 
	public CollectionResult<Endowment> getEndowments(EndowmentParam param){
		try {
			//Fetch params
			Endowment endowment = new Endowment();
			Page page = new Page(param.getPage(),param.getRows());
			CollectionResult<Endowment> result = new CollectionResult<Endowment>();
			result.setRows(endowmentService.getEndowments(endowment,page));
			result.setTotal(endowmentService.countEndowments(endowment));
			return result;
		} catch (AppServiceException e) {
			logger.error("get endowments failed. params[message = " + e.getMessage() + "]", e);
		} 
		return null;
	}
	
	@RequestMapping("/add")
	@ResponseBody 
	public String addEndowment(Endowment endowment){
		try {
			String userName = this.getSessionUser().getUserName();
			endowment.setId(StringTool.generateID());
			endowment.setCreateuser(userName);
			endowment.setUpdateuser(userName);
			endowmentService.addEndowment(endowment);
			return JSONObject.toJSONString(new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功."));
		} catch (AppServiceException e) {
			logger.error("add endowment failed. params[UnitNo = " + endowment.getUnitNo() + ",Time = " + endowment.getTime() + "]", e);
			return JSONObject.toJSONString(new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage()));
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody 
	public CommonResult deleteEndowment(String id) {
		try {
			endowmentService.deleteEndowment(id);
			return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
		} catch (AppServiceException e) {
			logger.error("delete medical failed. params[id = " + id + "]", e);
			return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
		}
	}
	
	@RequestMapping("/update")
	@ResponseBody 
	public CommonResult updateEndowment(Endowment endowment){
		try {
			String userName = this.getSessionUser().getUserName();
			endowment.setUpdateuser(userName);
			endowmentService.updateEndowment(endowment);
			return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
		} catch (AppServiceException e) {
			logger.error("update endowment failed. params[id = " + endowment.getId() + "]", e);
			return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
		}
	}
	
	@RequestMapping("/copy")
	@ResponseBody 
	public CommonResult copyEndowment(Endowment endowment){
		try {
			String userName = this.getSessionUser().getUserName();
			endowment.setId(StringTool.generateID());
			endowment.setCreateuser(userName);
			endowment.setUpdateuser(userName);
			endowment.setUpdateuser(userName);
			endowmentService.addEndowment(endowment);
			return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
		} catch (AppServiceException e) {
			logger.error("update endowment failed. params[id = " + endowment.getId() + "]", e);
			return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
		}
	}
}
