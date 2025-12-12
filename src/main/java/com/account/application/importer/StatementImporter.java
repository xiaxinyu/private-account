package com.account.application.importer;

import com.account.domain.model.Credit;

import java.util.List;

public interface StatementImporter {
    List<Credit> parse(List<String[]> rows, String bankCode, String cardTypeCode, String cardNo);
}
