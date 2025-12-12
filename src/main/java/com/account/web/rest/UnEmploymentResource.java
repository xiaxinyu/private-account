package com.account.web.rest;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.core.StringTool;
import com.account.domain.model.Page;
import com.account.domain.model.UnEmployment;
import com.account.core.AppServiceException;
import com.account.application.IUnEmploymentService;
import com.account.web.rest.model.CollectionResult;
import com.account.web.rest.model.CommonResult;
import com.account.web.rest.model.ResultCode;
import com.account.web.rest.model.UnEmploymentParam;

@Controller
@RequestMapping("/unemployment")
public class UnEmploymentResource extends ControllerHelper {
    private static final Logger logger = LoggerFactory.getLogger(UnEmploymentResource.class);

    @Autowired
    private IUnEmploymentService unEmploymentService;

    @RequestMapping("/getUnEmployments")
    @ResponseBody
    public CollectionResult<UnEmployment> getUnEmployments(UnEmploymentParam param) {
        try {
            //Fetch params
            UnEmployment unEmployment = new UnEmployment();
            Page page = new Page(param.getPage(), param.getRows());
            CollectionResult<UnEmployment> result = new CollectionResult<UnEmployment>();
            result.setRows(unEmploymentService.getUnEmployments(unEmployment, page));
            result.setTotal(unEmploymentService.countUnEmployments(unEmployment));
            return result;
        } catch (AppServiceException e) {
            logger.error("get UnEmployments failed. params[message = " + e.getMessage() + "]", e);
        }
        return null;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addUnEmployment(UnEmployment unEmployment) {
        try {
            String userName = this.getSessionUser().getUserName();
            unEmployment.setId(StringTool.generateID());
            unEmployment.setCreateuser(userName);
            unEmployment.setUpdateuser(userName);
            unEmploymentService.addUnEmployment(unEmployment);
            return JSONObject.toJSONString(new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功."));
        } catch (AppServiceException e) {
            logger.error("add UnEmployment failed. params[UnitNo = " + unEmployment.getUnitNo() + ",Time = " + unEmployment.getTime() + "]", e);
            return JSONObject.toJSONString(new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage()));
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonResult deleteUnEmployment(String id) {
        try {
            unEmploymentService.deleteUnEmployment(id);
            return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
        } catch (AppServiceException e) {
            logger.error("delete UnEmployment failed. params[id = " + id + "]", e);
            return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public CommonResult updateUnEmployment(UnEmployment unEmployment) {
        try {
            String userName = this.getSessionUser().getUserName();
            unEmployment.setUpdateuser(userName);
            unEmploymentService.updateUnEmployment(unEmployment);
            return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
        } catch (AppServiceException e) {
            logger.error("update UnEmployment failed. params[id = " + unEmployment.getId() + "]", e);
            return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
        }
    }

    @RequestMapping("/copy")
    @ResponseBody
    public CommonResult copyUnEmployment(UnEmployment unEmployment) {
        try {
            String userName = this.getSessionUser().getUserName();
            unEmployment.setId(StringTool.generateID());
            unEmployment.setCreateuser(userName);
            unEmployment.setUpdateuser(userName);
            unEmployment.setUpdateuser(userName);
            unEmploymentService.addUnEmployment(unEmployment);
            return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
        } catch (AppServiceException e) {
            logger.error("update UnEmployment failed. params[id = " + unEmployment.getId() + "]", e);
            return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
        }
    }
}
