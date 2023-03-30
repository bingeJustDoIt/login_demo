package com.example.login_demo.controller;

import com.example.login_demo.aop.Aes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/main")
public class MainController {
    @RequestMapping("/test")
    @Aes
    public Object test() {
//        int i = 1 / 0;
        return "1. Hello world ! " + new SimpleDateFormat(" [yyyy-mm-dd  HH:mm:ss]").format(new Date());
    }

}
