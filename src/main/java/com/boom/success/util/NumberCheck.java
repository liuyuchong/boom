package com.boom.success.util;

public class NumberCheck {
    public static boolean in(Integer num, Integer from, Integer to) {
        if (num == null) {
            return false;
        }
        if (from == null) {
            return num == to;
        }
        if (to == null) {
            return num == from;
        }
        return num >= from && num <= to;
    }
}
