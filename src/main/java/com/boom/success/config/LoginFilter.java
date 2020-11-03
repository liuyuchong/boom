package com.boom.success.config;

import com.boom.success.consts.RoleEnums;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        if (session == null||session.getAttribute("username")==null) {
            response.sendError(401,"Not Authorized");
            return;
        }
        //不是登录相关和修改密码 guest用户只能看
        if (Objects.equals(session.getAttribute("role"), RoleEnums.GUEST.getCode())) {
            String uri = request.getRequestURI();
             if (uri.contains("login") || uri.contains("logout") || uri.contains("modifyPassword")) {
                //do nothing
            }else{
                String method = request.getMethod();
                if (!Objects.equals(method, "GET")) {
                    response.sendError(403, "访客只能查阅数据");
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
