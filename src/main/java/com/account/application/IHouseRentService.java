package com.account.application;

import java.util.List;

import com.account.domain.model.HouseRent;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;

public interface IHouseRentService {
    int countHouseRent(HouseRent houseRent) throws AppServiceException;

    List<HouseRent> getHouseRents(HouseRent houseRent, Page page) throws AppServiceException;
}
