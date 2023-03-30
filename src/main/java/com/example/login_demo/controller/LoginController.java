package com.example.login_demo.controller;

import com.example.login_demo.LoginForm;
import com.example.login_demo.Result;
import com.example.login_demo.aop.log.InOutLog;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/login")
@InOutLog
public class LoginController {
    private static final Map<String, String> userDetails;

    private static final ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();

    static {
        userDetails = new HashMap<>();
        userDetails.put("tom", "123");
    }



    @PostMapping("/login")
    @InOutLog
    public Object login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        System.out.println("token:"+authorization);
        String s = userDetails.get(loginForm.getUsername());
        if (!ObjectUtils.isEmpty(s) && s.equals(loginForm.getPassword())) {
            String token = UUID.randomUUID().toString();
            tokenMap.put(loginForm.getUsername(), token);
            System.out.println(token);
            return Result.success(token);
        }
        return Result.fail();
    }
}
