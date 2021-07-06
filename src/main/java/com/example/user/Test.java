package com.example.user;

import com.alibaba.fastjson.JSON;
import org.bouncycastle.util.Strings;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author shuixianbin
 * @date 2021/04/22
 */
public class Test {

    public Test(String a) {

    }

    public static class a {
        private String a;

    }

    public static class B extends a {
        private String a;

    }








    public static int a(int a, String b) {
        return 0;
    }

    public static boolean is(int c) {
        int a = (int)Math.sqrt(c);
        for (int i = 0; i <= a; i++) {
            double b = Math.sqrt(c - i * i);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }

    public static boolean is1(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }

    public static double a1(int nums1[], int nums2[]) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int[] newArray = merge(nums1, nums2);
        System.out.println(JSON.toJSONString(newArray));
        if (total % 2 == 0) {
            int x = total/2;
            int y = x - 1;
            return (newArray[x] + newArray[y])/2.0;
        } else {
            int x = total/2;
            return newArray[x];
        }
    }

    public static int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] newArray = new int[m + n];//定义一个新的数组，长度为两个原数组长度之和
        int i = 0, j = 0, count = 0;//定义三个指针，分别指向三个数组的第一个元素
        //当指针位置小于数组长度时，比较两个数组的元素的大小，小的放入新数组
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                newArray[count++] = nums1[i++];
            } else {
                newArray[count++] = nums2[j++];
            }
        }

        if (i >= m) {//当第一个数组遍历完成，直接将第二个数组元素放入新数组
            while (j < n) {
                newArray[count++] = nums2[j++];
            }
        }
        if (j <= n) {//当第一个数组遍历完成，直接将第二个数组元素放入新数组
            while (i < m) {
                newArray[count++] = nums1[i++];
            }
        }
        return newArray;

    }

    public static boolean isMatch(String s, String p) {
        Pattern p1 = Pattern.compile(p);
        return p1.matcher(s).matches();
    }

    public static int aa (int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int m = 0;
            for (int j = 0; j < nums.length; j++) {
                m += (nums[j] >> i) & 1;
            }
            res += m % 3 << i;
        }
        return res;
    }


    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("aa");
//        int b = 5;
//        int c;
//        int m;
//        c = 2;
//        System.out.println(new String("aaaaaa"));
//        String d = "bbbbbbb";
//        m = c + b;
//        a(1, d);
        //is(5);
        byte[][][] a = new byte[200][1024][1024];
        try {
            System.in.read();
        } catch (Exception e) {

        }

        //System.out.println(aa(new int[]{1, 2, 1, 1, 3, 3, 3}));
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        myRunnable.setS("aaaaaaaaaa");
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Map<String, Object> map = new HashMap<>();
//        map.put("a", new Integer(1));
//        map.put("b", "b");
//        map.put("c", new Date());
//
//        //System.out.println(ClassLayout.parseInstance(new AnnotationConfigApplicationContext()).toPrintable());
//        B b = new B();
//        System.out.println(ClassLayout.parseInstance(b).toPrintable());
//        //System.out.println(b.hashCode());
//        new Thread() {
//            @Override
//            public void run() {
//                synchronized (b){
//                    System.out.println("第一次加锁后:"+ClassLayout.parseInstance(b).toPrintable());
//                    //System.out.println(b.hashCode());
//                    System.out.println("第五加锁后:"+ClassLayout.parseInstance(b).toPrintable());
//                    try {
//                        Thread.sleep(2000);
//                    } catch (Exception e) {
//
//                    }
//                    System.out.println("第三次加锁后:"+ClassLayout.parseInstance(b).toPrintable());
//
//                }
//            }
//        }.start();
//
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//
//        }
//        new Thread() {
//            @Override
//            public void run() {
//                synchronized (b){
//                    System.out.println("第二次加锁后:"+ClassLayout.parseInstance(b).toPrintable());
//                    System.out.println(b.hashCode());
//                }
//            }
//        }.start();
//
//
//
//        try {
//            Thread.sleep(3000);
//        } catch (Exception e) {
//
//        }
//        System.out.println("第六加锁后:"+ClassLayout.parseInstance(b).toPrintable());

    }

    public static int tt(int n) {
        int count = 0;
        while (n > 0) {
            if ((n&1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    public static class MyRunnable implements Runnable {

        private String s;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        @Override
        public void run() {
            System.out.println(s);
        }
    }

}
