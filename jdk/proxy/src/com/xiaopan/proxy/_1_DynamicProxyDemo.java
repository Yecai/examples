package com.xiaopan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class _1_DynamicProxyDemo {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject(); // 1.创建委托对象
        ProxyHandler handler = new ProxyHandler(realSubject); // 2.创建调用处理器
        // 3.动态生成代理
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(), handler);
        proxySubject.request(); // 4.通过代理对象调用方法
    }
}

/** 接口 **/
interface Subject {
    void request();
}

/** 委托类 **/
class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("===RealSubject Request==========");
    }
}

class ProxyHandler implements InvocationHandler {
    private Subject subject;

    public ProxyHandler(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("===before=======");
        Object result = method.invoke(subject, args);
        System.out.println("===after========");
        return result;
    }

}