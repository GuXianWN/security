package com.demo.controller;

import com.demo.entity.Demo;
import com.demo.entity.R;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

@RequestMapping
@RestController
public class DemoController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/1")
    public Demo demo() {
        Demo demo = new Demo();
        demo.setR(R.success())
                .setDate(new Date());
        redisTemplate.opsForValue().set("1", demo);

        Object obj = redisTemplate.opsForValue().get("1");
        demo = (Demo) obj;
        return demo;
    }

    @Cacheable("1")
    @GetMapping("/2")
    public Demo demo2() {
        System.out.println("cache");
        return new Demo()
                .setR(R.success())
                .setDate(new Date());
    }
}
