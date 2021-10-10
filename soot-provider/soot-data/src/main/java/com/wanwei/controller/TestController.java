package com.wanwei.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/t")
    public String test(){
        String result = "soot-data:成功";
        log.error(result);
        return result;
    }

    @GetMapping("/t1")
    public String test1(){
        String result = "soot-data:成功";
        log.error(result);
        return result;
    }
}
