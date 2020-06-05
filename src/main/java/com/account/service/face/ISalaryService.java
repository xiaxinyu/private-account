package com.account.service.face;

import java.util.List;

import com.account.persist.model.Page;
import com.account.persist.model.Salary;
import com.account.service.exception.AppServiceException;

/**
 * Created by Summer.Xia on 12/12/2018.
 */
public interface ISalaryService {
	int countSalary(Salary salary) throws AppServiceException;

	public List<Salary> getSalarys(Salary salary, Page page) throws AppServiceException;
}
