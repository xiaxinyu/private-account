package com.account.web.rest.encrypt;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 加密Controller
 *
 * @author XIAXINYU3
 * @date 2020.6.4
 */
@Controller
@RequestMapping({"/encrypt"})
public class EncryptEndpointController {

    @Autowired
    StringEncryptor encryptor;

    public EncryptEndpointController() {
    }

    @ResponseBody
    @GetMapping
    public Map encrypt(@RequestParam String key) {
        String encryptKey = this.encryptor.encrypt(key);
        Map<String, String> map = new HashMap();
        map.put("key", key);
        map.put("encryptKey", encryptKey);
        return map;
    }
}