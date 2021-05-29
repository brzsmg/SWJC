package com.swlibs.common.system;

public class Strings {

    public static boolean isEmpty(String value) {
        if(value == null) {
            return true;
        } else {
            return value.equals("");
        }
    }

    public static boolean isNotEmpty(String value) {
        if(value == null) {
            return false;
        } else {
            return !value.equals("");
        }
    }

}
