package com.demo.controller;

import com.demo.entity.Demo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RequestMapping
@RestController
public class DemoController {
    @Resource
    private DemoService demoService;

    @PostMapping
    public void demo(Demo demo){
        demoService.getDemo(demo);
    }
}
