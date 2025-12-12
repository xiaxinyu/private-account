package com.account.application.importer;

import com.account.application.importer.impl.CcbCreditStatementImporter;

public class StatementImporterFactory {
    public static StatementImporter get(String bankCode, String cardTypeCode) {
        if ("CCB".equalsIgnoreCase(bankCode) && "credit".equalsIgnoreCase(cardTypeCode)) {
            return new CcbCreditStatementImporter();
        }
        return new CcbCreditStatementImporter();
    }
}
