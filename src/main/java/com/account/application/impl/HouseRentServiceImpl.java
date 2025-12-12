package com.account.application.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.core.DataStructureTool;
import com.account.core.DateTool;
import com.account.infrastructure.mapper.HouseRentMapper;
import com.account.domain.model.HouseRent;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;
import com.account.application.IHouseRentService;

/**
 * Created by Summer.Xia on 12/12/2018.
 */
@Service("houseRentService")
public class HouseRentServiceImpl implements IHouseRentService {
    @Autowired
    private HouseRentMapper houseRentMapper;

    public List<HouseRent> getHouseRents(HouseRent houseRent, Page page) throws AppServiceException {
        List<HouseRent> result = null;
        try {
            result = houseRentMapper.getHouseRents(houseRent, page);
            if (DataStructureTool.isNotEmpty(result)) {
                for (HouseRent item : result) {
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

    public int countHouseRent(HouseRent houseRent) throws AppServiceException {
        int result = 0;
        try {
            result = houseRentMapper.countHouseRent(houseRent);
        } catch (Exception e) {
            throw new AppServiceException(e);
        }
        return result;
    }
}
