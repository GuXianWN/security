package com.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class DemoController {
    @GetMapping("1")
    public Object demo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("2")
    public Object demo2() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
