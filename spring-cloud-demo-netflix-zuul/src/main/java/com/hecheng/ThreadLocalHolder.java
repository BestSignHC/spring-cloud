package com.hecheng;

public class ThreadLocalHolder {

    private static ThreadLocal<Long> requestTimeHolder = new ThreadLocal<>();

    public static ThreadLocal<Long> requestTimeHolder() {
        return requestTimeHolder;
    }
}
