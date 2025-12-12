package com.account.web.contoller;

import com.account.domain.credit.bo.CreditUploadBO;
import com.account.domain.model.CreditRecord;
import com.account.application.authentication.AuthenticationFacade;
import com.account.core.AppException;
import com.account.application.ICreditRecordService;
import com.account.application.ICreditService;
import com.account.application.card.BankCardService;
import com.account.domain.model.BankCard;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 信用卡 Controller
 *
 * @author XIAXINYU3
 * @date 2020.4.21
 */
@Controller
@RequestMapping(value = "/v1/credit")
public class CreditUploadController {
    private static final Logger log = LoggerFactory.getLogger(CreditUploadController.class);

    @Autowired
    ICreditService creditService;

    @Autowired
    ICreditRecordService creditRecordService;

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    BankCardService bankCardService;
    @Autowired
    com.account.application.consume.ClassificationService classificationService;

    @PostMapping("/upload.html")
    @Transactional(rollbackFor = Exception.class)
    public String uploadCreditBillFile(@RequestParam("creditBillFile") MultipartFile creditBillFile,
                                       @RequestParam(value = "bankCode", required = false) String bankCode,
                                       @RequestParam(value = "cardTypeCode", required = false) String cardTypeCode,
                                       @RequestParam(value = "cardNo", required = false) String cardNo,
                                       HttpServletRequest request) {
        String userName = authenticationFacade.getUserName();
        log.info("用户正在上传账单：userName={}", userName);

        File uploadFile = saveFile(creditBillFile, request);
        if (uploadFile.exists()) {
            CreditUploadBO creditUploadBo = readDataAuto(uploadFile);
            List<String[]> dataRows = creditUploadBo.getDataRows();
            CreditRecord creditRecord = creditUploadBo.getCreditRecord();

            creditRecordService.addCreditRecord(creditRecord, userName);
            List<com.account.domain.model.Credit> credits = com.account.application.importer.StatementImporterFactory
                    .get(StringUtils.trimToEmpty(bankCode), StringUtils.trimToEmpty(cardTypeCode))
                    .parse(dataRows, bankCode, cardTypeCode, cardNo);
            BankCard bankCard = bankCardService.getByBankTypeNo(StringUtils.trimToEmpty(bankCode), StringUtils.trimToEmpty(cardTypeCode), StringUtils.trimToEmpty(cardNo));
            if (bankCard == null && StringUtils.isNotBlank(cardNo)) {
                bankCard = bankCardService.getByCardNo(StringUtils.trimToEmpty(cardNo));
            }
            String bankCardId = bankCard == null ? null : bankCard.getId();
            String bankCardName = bankCard == null ? null : bankCard.getCardName();
            for (com.account.domain.model.Credit c : credits) {
                if (StringUtils.isNotBlank(bankCardId)) {
                    c.setBankCardId(bankCardId);
                    c.setBankCardName(bankCardName);
                }
                com.account.application.consume.ClassificationService.Result r = classificationService.classify(c.getTransactionDesc(), bankCode, cardTypeCode);
                if (r != null) {
                    c.setConsumeID(r.id);
                    c.setConsumeName(r.name);
                }
                c.setRecordID(creditRecord.getId());
            }
            creditService.addCredits(credits, userName);

            log.info("完成上传账单：userName={}, recordId={}", userName, creditRecord.getId());
        } else {
            throw new AppException("Can't file upload file in the server,upload file fail");
        }
        return "account/credit/credit_upload_success";
    }

    private File saveFile(MultipartFile creditBillFile, HttpServletRequest request) {
        try {
            String savePath = request.getServletContext().getRealPath("/") + "uploads" + File.separator + creditBillFile.getOriginalFilename();
            File file = new File(savePath);
            FileUtils.copyInputStreamToFile(creditBillFile.getInputStream(), file);
            return file;
        } catch (Exception e) {
            log.info("保存上传文件出现错误", e);
            throw new AppException(e.getMessage());
        }
    }

    public CreditUploadBO readData(File file, String strPrefix) {
        try {
            String strData = FileUtils.readFileToString(file, "utf-8");
            if (StringUtils.isBlank(strData)) {
                throw new AppException("...");
            }

            String[] rowDatas = strData.split("\n");
            List<String[]> datRows = new ArrayList<>();


            int counter = 0;
            for (String rowData : rowDatas) {
                rowData = StringUtils.trim(rowData);
                if (StringUtils.isBlank(rowData)) {
                    continue;
                }

                String[] values = rowData.split(strPrefix);
                if (values == null || values.length <= 0) {
                    continue;
                }

                datRows.add(values);
                counter++;
            }

            CreditRecord creditRecord = new CreditRecord();
            creditRecord.setBillData(strData);
            creditRecord.setBillItemsNumber(counter);

            CreditUploadBO creditUploadBo = new CreditUploadBO();
            creditUploadBo.setDataRows(datRows);
            creditUploadBo.setCreditRecord(creditRecord);
            return creditUploadBo;
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    public CreditUploadBO readDataAuto(File file) {
        try {
            String strData = FileUtils.readFileToString(file, "utf-8");
            if (StringUtils.isBlank(strData)) {
                throw new AppException("...");
            }
            String delimiter = detectDelimiter(strData);
            return readData(file, delimiter);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    private String detectDelimiter(String strData) {
        String[] lines = strData.split("\n");
        String first = lines.length > 0 ? lines[0] : strData;
        if (first.contains(",")) return ",";
        if (first.contains("\t")) return "\t";
        if (first.contains(";")) return ";";
        return ",";
    }
}
