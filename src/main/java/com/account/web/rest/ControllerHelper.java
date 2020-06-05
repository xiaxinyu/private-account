package com.account.web.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.account.persist.model.User;
import com.account.service.exception.AppServiceException;

/**
 * Created by Summer.Xia on 2015/10/8.
 */
public class ControllerHelper {
	@Autowired
    HttpSession session;
	
	public User getSessionUser() throws AppServiceException{
		User user = null;
		if(session != null){
			user = new User();
			String app_username = (String)session.getAttribute("app_username");
			user.setUserName(app_username);
		}else{
			throw new AppServiceException("you are not valid user!");
		}
		return user;
	}
}
