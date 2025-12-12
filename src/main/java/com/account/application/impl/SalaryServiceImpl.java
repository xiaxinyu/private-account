package com.account.application.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.core.DataStructureTool;
import com.account.core.DateTool;
import com.account.infrastructure.mapper.SalaryMapper;
import com.account.domain.model.Page;
import com.account.domain.model.Salary;
import com.account.core.AppServiceException;
import com.account.application.ISalaryService;

/**
 * Created by Summer.Xia on 12/12/2018.
 */
@Service("salaryService")
public class SalaryServiceImpl implements ISalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    public List<Salary> getSalarys(Salary salary, Page page) throws AppServiceException {
        List<Salary> result = null;
        try {
            result = salaryMapper.getSalarys(salary, page);
            if (DataStructureTool.isNotEmpty(result)) {
                for (Salary item : result) {
                    Date transactionDate = item.getTransactionDate();
                    item.setYear("--");
                    if (null != transactionDate) {
                        item.setYear(DateTool.changeDateToString(transactionDate, DateTool.DF_YYYY));
                    }
                }
            }
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }

    public int countSalary(Salary salary) throws AppServiceException {
        int result = 0;
        try {
            result = salaryMapper.countSalary(salary);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }
}
