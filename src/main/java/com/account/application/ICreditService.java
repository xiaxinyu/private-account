package com.account.application;

import com.account.domain.model.Credit;
import com.account.domain.model.Page;
import com.account.core.AppServiceException;

import java.util.List;

public interface ICreditService {
    void updateCredit(Credit credit, String userName) throws AppServiceException;

    void deleteCredit(String id) throws AppServiceException;

    List<Credit> getCredits(Credit credit, Page page) throws AppServiceException;

    int countCredit(Credit credit) throws AppServiceException;

    void addCredits(List<String[]> rowDatas, String customerName, String recordID);

    void addCredits(List<Credit> credits, String userName);

    String consumeReport(Credit credit) throws AppServiceException;

    String weekConsumeReport(Credit credit) throws AppServiceException;

    String monthConsumeReport(Credit credit) throws AppServiceException;
}
