package com.account.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.account.domain.model.HouseRent;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;
import com.account.application.IHouseRentService;
import com.account.web.rest.model.CollectionResult;
import com.account.web.rest.model.HouseRentParam;

@Controller
@RequestMapping("/house-rent")
public class HouseRentResource extends ControllerHelper {
	private static final Logger logger = LoggerFactory.getLogger(HouseRentResource.class);

	@Autowired
	private IHouseRentService houseRentService;

	@RequestMapping("/getHouseRents")
	@ResponseBody
	public CollectionResult<HouseRent> getHouseRents(HouseRentParam param) {
		try {
			// Fetch params
			HouseRent houseRent = new HouseRent();
			Page page = new Page(param.getPage(), param.getRows());
			CollectionResult<HouseRent> result = new CollectionResult<HouseRent>();
			result.setRows(houseRentService.getHouseRents(houseRent, page));
			result.setTotal(houseRentService.countHouseRent(houseRent));
			return result;
		} catch (AppServiceException e) {
			logger.error("get medicals failed. params[message = " + e.getMessage() + "]", e);
		}
		return null;
	}
}
