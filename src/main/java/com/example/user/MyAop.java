package com.example.user;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author shuixianbin
 * @date 2021/06/15
 */
public class MyAop implements MethodInterceptor {

    //private Enhancer enhancer =  new Enhancer();

//    public Object getProxy(Class clazz) {
//        // 设置需要创建子类的类
//        enhancer.setSuperclass(clazz);
//        enhancer.setCallback(this);
//        // 通过字节码技术动态创建子类实例
//        return  enhancer.create();
//    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return result;
    }

    public static Object getProxy2(Class clazz, Callback o) {
        Enhancer enhancer =  new Enhancer();
        // 设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(o);
        // 通过字节码技术动态创建子类实例
        return  enhancer.create();
    }

    public  static  void  main(String[]  args)  {
        long start = System.nanoTime();
        for (int i = 0; i < 100000000; i++) {

        }
        long end = System.nanoTime();
        System.out.println(start);
        System.out.println(end - start);
        MyAop  proxy  =  new  MyAop();
        // 通过动态生成子类的方式创建代理类
        CglibTask  task  =  (CglibTask)getProxy2(CglibTask.class, proxy);
        task.task();
    }

    public static class CglibTask {
        private String a = "mmm";
        public void task() {
            System.out.println("hello");
        }
    }

}
