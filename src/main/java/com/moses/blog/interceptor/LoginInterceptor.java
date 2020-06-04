package com.moses.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登陆拦截器,如果用户没有登陆就尝试访问某些url,将被重定向到登陆页面
 *
 * @author Moses
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1 获取请求的地址
        String url = request.getRequestURI();
        // 2 对特殊地址，直接放行
        System.out.println("请求的链接是" + url);

        // 3 判断session，session存在的话，登录后台
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("id");
        if (obj!=null) {
            // 身份存在，放行
            System.out.println("请求的链接是" + url);
            System.out.println("已放行");
            return true;
        }

        // 4 执行这里表示用户身份需要验证，跳转到登录界面
        System.out.println("请求的链接是" + url);
        System.out.println("请登录");
        request.getRequestDispatcher("/WEB-INF/page/admin/login.jsp").forward(request, response);

        return false;

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
