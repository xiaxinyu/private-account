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

    @RequestMapping("/account/salary/Salary.jsp")
    public String salary(ModelMap model) {
        log.info("************ Hello, Salary in Private Account ************");
        return "account/salary/Salary";
    }

    @RequestMapping("/account/salary/Salary.html")
    public String salaryHtml(ModelMap model) {
        log.info("************ Hello, Salary HTML in Private Account ************");
        return "account/salary/Salary";
    }

    @RequestMapping("/account/salary")
    public String salaryRest(ModelMap model) {
        log.info("************ Hello, Salary REST in Private Account ************");
        return "account/salary/Salary";
    }

    @RequestMapping("/account/house-rent/HouseRent.jsp")
    public String houseRent(ModelMap model) {
        log.info("************ Hello, HouseRent in Private Account ************");
        return "account/house-rent/HouseRent";
    }

    @RequestMapping("/account/house-rent/HouseRent.html")
    public String houseRentHtml(ModelMap model) {
        log.info("************ Hello, HouseRent HTML in Private Account ************");
        return "account/house-rent/HouseRent";
    }

    @RequestMapping("/account/house-rent")
    public String houseRentRest(ModelMap model) {
        log.info("************ Hello, HouseRent REST in Private Account ************");
        return "account/house-rent/HouseRent";
    }

    @RequestMapping("/account/endowment/Endowment.jsp")
    public String endowment(ModelMap model) {
        log.info("************ Hello, Endowment in Private Account ************");
        return "account/endowment/Endowment";
    }

    @RequestMapping("/account/endowment/Endowment.html")
    public String endowmentHtml(ModelMap model) {
        log.info("************ Hello, Endowment HTML in Private Account ************");
        return "account/endowment/Endowment";
    }

    @RequestMapping("/account/endowment")
    public String endowmentRest(ModelMap model) {
        log.info("************ Hello, Endowment REST in Private Account ************");
        return "account/endowment/Endowment";
    }

    @RequestMapping("/account/accumulation/accumulation.jsp")
    public String accumulation(ModelMap model) {
        log.info("************ Hello, Accumulation in Private Account ************");
        return "account/accumulation/accumulation";
    }

    @RequestMapping("/account/accumulation/accumulation.html")
    public String accumulationHtml(ModelMap model) {
        log.info("************ Hello, Accumulation HTML in Private Account ************");
        return "account/accumulation/accumulation";
    }

    @RequestMapping("/account/accumulation")
    public String accumulationRest(ModelMap model) {
        log.info("************ Hello, Accumulation REST in Private Account ************");
        return "account/accumulation/accumulation";
    }

    @RequestMapping("/account/medical/Medical.jsp")
    public String medical(ModelMap model) {
        log.info("************ Hello, Medical in Private Account ************");
        return "account/medical/Medical";
    }

    @RequestMapping("/account/medical/Medical.html")
    public String medicalHtml(ModelMap model) {
        log.info("************ Hello, Medical HTML in Private Account ************");
        return "account/medical/Medical";
    }

    @RequestMapping("/account/medical")
    public String medicalRest(ModelMap model) {
        log.info("************ Hello, Medical REST in Private Account ************");
        return "account/medical/Medical";
    }

    @RequestMapping("/account/unemployment/UnEmployment.jsp")
    public String unemployment(ModelMap model) {
        log.info("************ Hello, UnEmployment in Private Account ************");
        return "account/unemployment/UnEmployment";
    }

    @RequestMapping("/account/unemployment/UnEmployment.html")
    public String unemploymentHtml(ModelMap model) {
        log.info("************ Hello, UnEmployment HTML in Private Account ************");
        return "account/unemployment/UnEmployment";
    }

    @RequestMapping("/account/unemployment")
    public String unemploymentRest(ModelMap model) {
        log.info("************ Hello, UnEmployment REST in Private Account ************");
        return "account/unemployment/UnEmployment";
    }

    @RequestMapping("/system/admin/consume_rules.html")
    public String consumeRules(ModelMap model) {
        log.info("************ Hello, Category Rules in Private Account ************");
        return "system/admin/consume_rules";
    }

    @RequestMapping("/system/admin/consume_categories.html")
    public String consumeCategories(ModelMap model) {
        log.info("************ Hello, Category Types in Private Account ************");
        return "system/admin/consume_categories";
    }

    @RequestMapping("/system/admin/cards.html")
    public String bankCards(ModelMap model) {
        log.info("************ Hello, Bank Cards in Private Account ************");
        return "system/admin/cards";
    }
}
