package com.account.application;

import java.util.List;

import com.account.domain.model.Endowment;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;

public interface IEndowmentService {
    void addEndowment(Endowment endowment) throws AppServiceException;

    void updateEndowment(Endowment endowment) throws AppServiceException;

    void deleteEndowment(String id) throws AppServiceException;

    int countEndowments(Endowment endowment) throws AppServiceException;

    List<Endowment> getEndowments(Endowment endowment, Page page) throws AppServiceException;
}
