package com.example.user.service;

import com.example.user.util.RedisUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;


//@RunWith(SpringRunner.class)
//@SpringBootTest
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
//        System.out.println(redisUtil.set("asd", "sssss"));
//        System.out.println(redisUtil.get("asd"));
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "sss";
            }
        });

        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            String s = futureTask.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(ClassLayout.parseInstance(Foo.class).toPrintable());
    }

    static class Foo {
        private static String a = "22";
        private AtomicInteger firstJobDone = new AtomicInteger(0);
        private AtomicInteger secondJobDone = new AtomicInteger(0);
        public void first(Runnable runnable) {
            runnable.run();
            System.out.println("first");
            firstJobDone.incrementAndGet();
        }

        public void secode(Runnable runnable) {
            while (!firstJobDone.equals(1)) {

            }
            runnable.run();
            secondJobDone.incrementAndGet();
            System.out.println("secode");
        }

        public void third(Runnable runnable) {
            while (!secondJobDone.equals(1)) {

            }
            runnable.run();
            System.out.println("third");
        }

    }

    public <T extends Comparable<T>> List<T> merge() {
        List<T> result = new ArrayList<>();
        return result;
    }
}
