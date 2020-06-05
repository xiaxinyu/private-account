package com.account.web.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class AccountController {
    @RequestMapping("account/index.html")
    public String index(ModelMap model) {
        log.info("************ Hello, Account in Private Account ************");
        return "account/index";
    }

    @RequestMapping("/account/credit/credit_bill.html")
    public String creditBill(ModelMap model) {
        log.info("************ Hello, Credit Bill in Private Account ************");
        return "account/credit/credit_bill";
    }

    @RequestMapping("/account/credit/creidt_upload.html")
    public String creidtUpload(ModelMap model) {
        log.info("************ Hello, Creidt Upload in Private Account ************");
        return "account/credit/creidt_upload";
    }


    @RequestMapping("/account/credit/report/consume_line_report.html")
    public String consumeLineReport(ModelMap model) {
        log.info("************ Hello, Consume Line Report Private Account ************");
        return "account/credit/report/consume_line_report";
    }

    @RequestMapping("/account/credit/report/consume_pie_report.html")
    public String consumePieReport(ModelMap model) {
        log.info("************ Hello, Consume Pie Report Private Account ************");
        return "account/credit/report/consume_pie_report";
    }

    @RequestMapping("/account/credit/report/month_consume_report.html")
    public String monthConsumeReport(ModelMap model) {
        log.info("************ Hello, Month Consume Report Private Account ************");
        return "account/credit/report/month_consume_report";
    }

    @RequestMapping("/account/credit/report/week_consume_report.html")
    public String weekConsumeReport(ModelMap model) {
        log.info("************ Hello, Week Consume Report Private Account ************");
        return "account/credit/report/week_consume_report";
    }

}
