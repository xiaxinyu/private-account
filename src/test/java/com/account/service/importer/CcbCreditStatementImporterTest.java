package com.account.service.importer;

import com.account.persist.model.Credit;
import com.account.service.importer.impl.CcbCreditStatementImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CcbCreditStatementImporterTest {

    @Test
    public void parseCcbCredit() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"中国建设银行"});
        rows.add(new String[]{"中国建设银行信用卡交易明细"});
        rows.add(new String[]{"交易日","入账日","信用卡卡号","类型","入账币种","入账金额","交易描述"});
        rows.add(new String[]{"20251205","20251205","'6227080866603992","分期","人民币","83.33","深圳市 跨行消费 深圳市福田区:49/60"});
        rows.add(new String[]{"20251205","20251205","'6227080866603992","分期","人民币","583.33","深圳市 跨行消费 优品文创科技:49/60"});
        rows.add(new String[]{"20251205","20251205","'6227080866603992","分期","人民币","1666.67","深圳市 跨行消费 朗达实业有限:49/60"});

        StatementImporter importer = new CcbCreditStatementImporter();
        List<Credit> credits = importer.parse(rows, "CCB", "credit", "6227080866603992");
        Assertions.assertEquals(3, credits.size());
        Credit c = credits.get(0);
        Assertions.assertEquals("6227080866603992", c.getCardId());
        Assertions.assertEquals("人民币", c.getBalanceCurrency());
        Assertions.assertEquals(83.33, c.getBalanceMoney());
        Assertions.assertNotNull(c.getTransactionDate());
        Assertions.assertNotNull(c.getBookKeepingDate());
    }
}

