package com.account.service.authentication.impl;

import com.account.service.authentication.AuthenticationFacade;
import com.account.service.exception.AppException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getUserName() {
        Authentication authentication = getAuthentication();
        if (Objects.isNull(authentication)) {
            throw new AppException("error.system.error");
        }
        return authentication.getName();
    }
}
