package com.account.application;

import java.util.List;

import com.account.domain.model.Page;
import com.account.domain.model.Salary;
import com.account.core.AppServiceException;

/**
 * Created by Summer.Xia on 12/12/2018.
 */
public interface ISalaryService {
    int countSalary(Salary salary) throws AppServiceException;

    List<Salary> getSalarys(Salary salary, Page page) throws AppServiceException;
}
