package com.account.web.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    @RequestMapping({"", "index.html"})
    public String index(ModelMap model) {
        log.info("************ Hello, Private Account ************");
        return "index";
    }

    @RequestMapping("north.html")
    public String north(ModelMap model) {
        log.info("************ Hello,North in Private Account ************");
        return "system/navigation/north";
    }

    @RequestMapping("menu.html")
    public String menu(ModelMap model) {
        log.info("************ Hello,Menu in Private Account ************");
        return "system/navigation/menu";
    }

    @RequestMapping("navigation.html")
    public String navigation(ModelMap model) {
        log.info("************ Hello,Navigation in Private Account ************");
        return "system/navigation/navigation";
    }

    @RequestMapping("login.html")
    public String login(ModelMap model) {
        log.info("************ Hello,Login in Private Account ************");
        return "login";
    }

    @RequestMapping("login-error.html")
    public String loginError(ModelMap model) {
        log.info("************ Hello,Login Error in Private Account ************");
        return "system/login-error";
    }
}
