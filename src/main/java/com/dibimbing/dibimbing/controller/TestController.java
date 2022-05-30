package com.dibimbing.dibimbing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1/praktek/")
class TestController {
    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return   "Hello Spring Boot";
    }
}

