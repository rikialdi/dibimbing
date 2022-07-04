package com.dibimbing.dibimbing.controller;

import com.dibimbing.dibimbing.model.TestBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1/praktek/")
class TestController {

    @Autowired
    public TestBeans testBeans;

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return   "Hello Spring Boot";
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String hello1() {
        return   testBeans.simpanBarang();
    }
}

