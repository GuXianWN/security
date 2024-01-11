package com.demo;

import com.demo.service.QTimeService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    private QTimeService qTimeService;


}
