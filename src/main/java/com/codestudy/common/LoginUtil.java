package com.codestudy.common;

/**
 * 保存用户上下文
 */
public class LoginUtil {

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal();

    public static void setCurrentId(Integer id) {
        threadLocal.set(id);
    }

    public static Integer getCurrentId() {
        return threadLocal.get();
    }

}
