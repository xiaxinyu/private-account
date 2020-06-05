package com.account.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.persist.model.Page;
import com.account.persist.model.Salary;
import com.account.service.exception.AppServiceException;
import com.account.service.face.ISalaryService;
import com.account.web.rest.model.CollectionResult;
import com.account.web.rest.model.CreditParam;

@Controller
@RequestMapping("/salary")
public class SalaryResource extends ControllerHelper {
	private static final Logger logger = LoggerFactory.getLogger(SalaryResource.class);

	@Autowired
	private ISalaryService salaryService;

	@RequestMapping("/getSalarys")
	@ResponseBody
	public CollectionResult<Salary> getSalarys(CreditParam param) {
		try {
			// Fetch params
			Salary salary = new Salary();
			Page page = new Page(param.getPage(), param.getRows());
			CollectionResult<Salary> result = new CollectionResult<Salary>();
			result.setRows(salaryService.getSalarys(salary, page));
			result.setTotal(salaryService.countSalary(salary));
			return result;
		} catch (AppServiceException e) {
			logger.error("get medicals failed. params[message = " + e.getMessage() + "]", e);
		}
		return null;
	}
}
