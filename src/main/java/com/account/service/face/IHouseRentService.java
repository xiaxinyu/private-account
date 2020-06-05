package com.account.service.face;

import java.util.List;

import com.account.persist.model.HouseRent;
import com.account.persist.model.Page;
import com.account.service.exception.AppServiceException;

/**
 * Created by Summer.Xia on 12/12/2018.
 */
public interface IHouseRentService {
	int countHouseRent(HouseRent houseRent) throws AppServiceException;

	public List<HouseRent> getHouseRents(HouseRent houseRent, Page page) throws AppServiceException;
}
