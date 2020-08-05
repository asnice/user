package com.example.user.service;

import com.example.user.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloSenderTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void testSend() {
        helloSender.send();
    }

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void setRedisUtil() {
        System.out.println(redisUtil.set("asd", "sssss"));
        System.out.println(redisUtil.get("asd"));
    }

}
