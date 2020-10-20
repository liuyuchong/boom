package com.boom.success.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtil {
    public static String getUsername(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }
        Object username = session.getAttribute("username");
        if (username == null) {
            return null;
        }
        return username.toString();
    }
}
