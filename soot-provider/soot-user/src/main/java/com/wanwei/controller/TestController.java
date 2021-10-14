package com.wanwei.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class TestController {

    @GetMapping("/t")
    public String test(){
        String result = "soot-user:成功";
        log.error(result);
        return result;
    }
}
