package com.yang.model;

/**
 * User: jianyuanyang
 * Date: 13-4-17
 * Time: 下午3:57
 */
public class TestBean {

    public  static String  myName ;

    public static String myPassword ;

    public static String getMyName() {
        return myName;
    }

    public static void setMyName(String myName) {
        TestBean.myName = myName;
    }

    public static String getMyPassword() {
        return myPassword;
    }

    public static void setMyPassword(String myPassword) {
        TestBean.myPassword = myPassword;
    }
}
