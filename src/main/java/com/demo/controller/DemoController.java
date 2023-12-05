package com.demo.controller;

import cn.hutool.http.HttpUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping
@RestController
public class DemoController {
    @GetMapping("1")
    public Object demo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("2")
    @PreAuthorize("hasRole('2')")
    public Object demo2() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("callback")
    public String getTokenByCode(String code) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("code", code);
        paramMap.put("client_id", "656f0e8321b9f2bba9fbf9a7");
        paramMap.put("client_secret", "75a86dd4199bdcf86f92333559a94375");
        paramMap.put("grant_type", "authorization_code");
        paramMap.put("redirect_uri", "http://localhost:8080/callback");
        return HttpUtil.post("https://uxrb8nkx6a3k-demo.authing.cn/oidc/token", paramMap);
    }
}
