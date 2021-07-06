package com.example.user.controller;

import com.example.user.service.HelloSender;
import com.example.user.util.RedisUtil;
import com.example.user.util.ZookeeperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ZookeeperUtil zookeeperUtil;

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/test")
    public Object test() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("s", "tes");
        map.put("asda", "asda");
        return zookeeperUtil.isMaster();


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



//    public static void main(String[] args) {
//
//        HelloSender helloSender = new HelloSender();
//
//        helloSender.send();
//    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        TestController a = new TestController();
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread1 locking");
                    //System.out.println(ClassLayout.parseInstance(a).toPrintable());
                    try {
                        //让线程晚点儿死亡，造成锁的竞争
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread2 locking");
                    //System.out.println(ClassLayout.parseInstance(a).toPrintable());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();
        thread2.start();

//        String s = " dd dsf asda dd";
//        System.out.println(s.replace(" ", ""));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(Math.pow(2,31)-1);
//        double l = 2D*2.0;
//        double m = 4D;
//        float f = 2l;
//        int a = 011;
//        System.out.println(a);
//        System.out.println(2*f==4);
//        List<String> list = new ArrayList<>();
//        list.add(null);
//        list.add(null);
//        System.out.println(list);
//        String json = "[{\"dingding_user_id\" : \"asdasd\", \"user_id\" : \"adadad\"},{\"dingding_user_id\" : \"15535135\", \"user_id\" : \"asdasdasdas\"}]";
//        JSONArray jsonArray = JSON.parseArray(json);
//
//        for (Object o : jsonArray) {
//            Map<String, Object> pusher = (Map<String, Object>)o;
//
//            System.out.println(pusher.get("dingding_user_id").toString());
//            System.out.println(pusher.get("user_id").toString());
//        }
        String s = "123456";
        //System.out.println(s.substring(0, 7));

        List<String> list = new ArrayList();
        System.out.println(list.size());
        for (String s1 : list) {
            System.out.println(s1);
        }
        LOGGER.debug("debug");
        LOGGER.info("info");


    }

}
