package com.account.application;

import java.util.List;

import com.account.domain.model.Accumulation;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;

public interface IAccumulationService {
    void addAccumulation(Accumulation accumulation) throws AppServiceException;

    void updateAccumulation(Accumulation accumulation) throws AppServiceException;

    void deleteAccumulation(String id) throws AppServiceException;

    int countAccumulations(Accumulation accumulation) throws AppServiceException;

    List<Accumulation> getAccumulations(Accumulation accumulation, Page page) throws AppServiceException;
}
