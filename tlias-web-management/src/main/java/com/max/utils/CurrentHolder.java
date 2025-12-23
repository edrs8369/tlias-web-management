package com.max.utils;

public class CurrentHolder {

    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    //存id到threadLocal變數中
    public static void setCurrentId(Integer employeeId) { CURRENT_LOCAL.set(employeeId);}

    //取值
    public static Integer getCurrentId() { return CURRENT_LOCAL.get();}

    //移除
    public static void remove() { CURRENT_LOCAL.remove();}
}
