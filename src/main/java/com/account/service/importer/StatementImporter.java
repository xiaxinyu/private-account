package com.account.service.importer;

import com.account.persist.model.Credit;

import java.util.List;

public interface StatementImporter {
    List<Credit> parse(List<String[]> rows, String bankCode, String cardTypeCode, String cardNo);
}

