package com.example.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class TestController {

    @PostMapping("/test")
    public Object test(@RequestParam(required = false) Boolean map) {
        Object object = new Object();
        String s = "test";

        return map;


    }

    @PostMapping("/testFile")
    public Object testFile(@RequestParam("file") MultipartFile file) {
        Object object = new Object();
        String s = "test";

        return s;


    }

    public static void main(String[] args) {
        System.out.println(
                );
    }
}
