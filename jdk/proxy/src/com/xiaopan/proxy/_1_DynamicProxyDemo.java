package com.xiaopan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class _1_DynamicProxyDemo {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject(); // 1.����ί�ж���
        ProxyHandler handler = new ProxyHandler(realSubject); // 2.�������ô�����
        // 3.��̬���ɴ���
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(), handler);
        proxySubject.request(); // 4.ͨ�����������÷���
    }
}

/** �ӿ� **/
interface Subject {
    void request();
}

/** ί���� **/
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