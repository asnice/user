package com.example.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    private static Runnable getThread(final int i){
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        };
    }

//    public static void main(String[] args) {
//        Instant instant = Instant.now();
//        LocalDate localDate = LocalDate.now();
//        LocalDateTime localDateTime =  LocalDateTime.now();
//        System.out.println(instant);
//        System.out.println(localDate);
//        System.out.println(localDateTime);
//
//        //创建一个可缓存线程池
//        ExecutorService cachePool = Executors.newCachedThreadPool();
//        for (int i=1;i<=10;i++){
//            cachePool.execute(getThread(i));
//        }
//
//
//        ExecutorService fixPool = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            fixPool.execute(getThread(i));
//        }
//        fixPool.shutdown();
//
//        ExecutorService singPool = Executors.newSingleThreadExecutor();
//        for (int i=0;i<10;i++){
//            singPool.execute(getThread(i));
//        }
//        singPool.shutdown();
//
//        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
//        ses.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(4000);
//                    System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getId() + "执行了");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 0, 2, TimeUnit.SECONDS);
//
//        System.out.println(Runtime.getRuntime().availableProcessors());
//
//    }


}
