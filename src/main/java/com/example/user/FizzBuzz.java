package com.example.user;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @author shuixianbin
 * @date 2021/05/28
 */
public class FizzBuzz  {
    private int n;
    private AtomicInteger i = new AtomicInteger(1);
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

        for (int m = 1; m <= n; m = i.intValue()) {
            if (m%3 == 0 && m%5 != 0 ) {
                printFizz.run();
                i.incrementAndGet();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {

        for (int m = 1; m <= n; m = i.intValue()) {
            if (m%3 != 0 && m%5 == 0 ) {
                printBuzz.run();
                i.incrementAndGet();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int m = 1; m <= n; m = i.intValue()) {
            if (m%3 == 0 && m%5 == 0 ) {
                printFizzBuzz.run();
                i.incrementAndGet();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int m = 1; m <= n; m = i.intValue()) {
            if (m%3 != 0 && m%5 != 0 ) {
                printNumber.accept(m);
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(20);

        Runnable thread1 = ()-> {

                try {
                    fizzBuzz.fizz(()->System.out.println("fizz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.buzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("buzz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizzbuzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("fizzbuzz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.number(new IntConsumer(){
                        @Override
                        public void accept(int value) {
                            System.out.println(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ((Thread)thread1).start();
        thread2.start();
        thread3.start();
        thread4.start();

    }




}

