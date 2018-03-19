package com.xiaopan.thread.unsafe.api;

import com.xiaopan.thread.unsafe._1_GetUnsafe;

import sun.misc.Unsafe;

/**
 * @Package: com.xiaopan.thread.unsafe.api
 * @ClassName: _1_allocateInstance
 * @date: 2018��3��19�� ����9:12:55
 * @Description: https://www.cnblogs.com/chenpi/p/5389254.html
 * allocateInstance�����������ù��췽�����ɶ���
 */
public class _1_allocateInstance {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException, InstantiationException {
        Unsafe unsafe = _1_GetUnsafe.getUnsafe();

        User user = (User) unsafe.allocateInstance(User.class);
        System.out.println(user); // dont invoke constructor, print null: 0

        User userFromNormal = new User();
        System.out.println(userFromNormal); // print test: 22

    }
}

class User {
    private String name = "";
    private int age = 0;

    public User() {
        this.name = "test";
        this.age = 22;
    }

    @Override
    public String toString() {
        return name + ": " + age;
    }
}