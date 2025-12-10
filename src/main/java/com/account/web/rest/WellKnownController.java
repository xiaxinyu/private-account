package com.account.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/.well-known/appspecific")
public class WellKnownController {

    @GetMapping("/com.chrome.devtools.json")
    @ResponseBody
    public Map<String, Object> chromeDevtools() {
        return Collections.emptyMap();
    }
}

