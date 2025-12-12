package com.account.web.rest;

import com.account.application.authentication.AuthenticationFacade;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.core.DateParseException;
import com.account.core.DateTool;
import com.account.core.StringTool;
import com.account.domain.model.Credit;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;
import com.account.application.ICreditService;
import com.account.web.rest.model.CollectionResult;
import com.account.web.rest.model.CommonResult;
import com.account.web.rest.model.CreditParam;
import com.account.web.rest.model.ResultCode;
import com.account.application.consume.ClassificationService;

@Controller
public class CreditController {
    private static final Logger logger = LoggerFactory.getLogger(CreditController.class);

    @Autowired
    ICreditService creditService;

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    ClassificationService classificationService;

    @RequestMapping("/credit/getCredits")
    @ResponseBody
    public CollectionResult<Credit> getCredits(CreditParam param) {
        try {
            //Fetch params
            Credit credit = new Credit();
            if (!StringTool.isNullOrEmpty(param.getTransactionDateStartStr())) {
                credit.setTransactionDateStart(DateTool.changeStringToDate(param.getTransactionDateStartStr(), DateTool.DF_MM_DD_YYYY));
            }
            if (!StringTool.isNullOrEmpty(param.getTransactionDateEndStr())) {
                credit.setTransactionDateEnd(DateTool.changeStringToDate(param.getTransactionDateEndStr(), DateTool.DF_MM_DD_YYYY));
            }
            if (!StringTool.isNullOrEmpty(param.getConsumptionType())) {
                credit.setConsumptionType(StringTool.changeObjToInt(StringUtils.trim(param.getConsumptionType())));
            }
            if (!StringTool.isNullOrEmpty(param.getCardTypeName())) {
                credit.setCardTypeName(StringUtils.trim(param.getCardTypeName()));
            }
            if (!StringTool.isNullOrEmpty(param.getCardId())) {
                credit.setBankCardId(StringUtils.trim(param.getCardId()));
            }
            if (!StringTool.isNullOrEmpty(param.getConsumeName())) {
                credit.setConsumeName(StringUtils.trim(param.getConsumeName()));
            }
            if (!StringTool.isNullOrEmpty(param.getConsumeID())) {
                credit.setConsumes(param.getConsumeID().split(","));
            }
            if (!StringTool.isNullOrEmpty(param.getDemoArea())) {
                credit.setDemoArea(StringUtils.trim(param.getDemoArea()));
            }
            if (!StringTool.isNullOrEmpty(param.getWeekName())) {
                credit.setWeekName(StringUtils.trim(param.getWeekName()));
            }
            if (!StringTool.isNullOrEmpty(param.getYear())) {
                credit.setYear(StringUtils.trim(param.getYear()));
            }
            if (!StringTool.isNullOrEmpty(param.getMonth())) {
                credit.setMonth(DateTool.getMonthCode(StringUtils.trim(param.getMonth())));
            }
            Page page = new Page(param.getPage(), param.getRows());

            CollectionResult<Credit> result = new CollectionResult<Credit>();
            StopWatch stopWatch = new StopWatch("消费数据查询统计");
            stopWatch.start("查询列表数据");
            result.setRows(creditService.getCredits(credit, page));
            stopWatch.stop();

            stopWatch.start("查询统计数据");
            result.setTotal(creditService.countCredit(credit));
            stopWatch.stop();

            String prettyPrint = stopWatch.prettyPrint();
            logger.info("耗时打印：{}", prettyPrint);

            return result;
        } catch (AppServiceException e) {
            logger.error("get credits failed. params[message = " + e.getMessage() + "]", e);
        } catch (DateParseException e) {
            logger.error("date type's params error. params[message = " + e.getMessage() + "]", e);
        }
        CollectionResult<Credit> empty = new CollectionResult<>();
        empty.setTotal(0);
        empty.setRows(java.util.Collections.emptyList());
        return empty;
    }

    @RequestMapping("/credit/delete")
    @ResponseBody
    public CommonResult deleteCredit(String id) {
        try {
            creditService.deleteCredit(id);
            return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
        } catch (AppServiceException e) {
            logger.error("delete credit failed. params[id = " + id + "]", e);
            return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
        }
    }

    @RequestMapping("/credit/update")
    @ResponseBody
    public CommonResult updateCredit(Credit credit) {
        try {
            creditService.updateCredit(credit, authenticationFacade.getUserName());
            return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), "操作成功.");
        } catch (AppServiceException e) {
            logger.error("delete credit failed. params[id = " + credit.getId() + ",consumptionType = " + credit.getConsumptionType() + "]", e);
            return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
        }
    }

    @RequestMapping("/credit/classify")
    @ResponseBody
    public CommonResult classifyCredit(Credit credit){
        try{
            String narration = credit.getTransactionDesc();
            String cardTypeCode = credit.getCardTypeName();
            if(cardTypeCode != null){ cardTypeCode = cardTypeCode.trim().toLowerCase(); }
            java.util.List<com.account.application.consume.ClassificationService.Result> rs = classificationService.classifyTopN(narration, null, cardTypeCode, 5);
            if(rs == null || rs.isEmpty()){
                return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), "no_match");
            }
            String payload = JSON.toJSONString(rs);
            return new CommonResult(ResultCode.OPERATION_SUCCEED.getCodeValue(), payload);
        }catch(Exception e){
            logger.error("classify credit failed. params[desc = " + credit.getTransactionDesc() + "]", e);
            return new CommonResult(ResultCode.OPERATION_FAILED.getCodeValue(), e.getMessage());
        }
    }
}
