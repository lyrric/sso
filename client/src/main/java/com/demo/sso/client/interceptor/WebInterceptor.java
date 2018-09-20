package com.demo.sso.client.interceptor;

import com.demo.sso.client.annotation.LoginRequired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.MvcNamespaceHandler;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created on 2018/9/19.
 *
 * @author wangxiaodong
 */
@Component
public class WebInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${sso.url}")
    private String ssoUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("---------------------------------------------------------"+ssoUrl);
        //uuid登陆返回请求
        Object uuid = request.getParameter("uuid");
        if(uuid != null){
            response.addCookie(new Cookie("uuid", uuid.toString()));
        }
        LoginRequired loginRequired = ((HandlerMethod)handler).getMethod().getAnnotation(LoginRequired.class);
        if(loginRequired != null){
            //是否登陆
            if(uuid == null){
                Cookie []cookies = request.getCookies();
                if(cookies != null){
                    for(Cookie cookie:cookies){
                        if("uuid".equals(cookie.getName())){
                            uuid = cookie.getValue();
                        }
                    }
                }
            }
            //检查UUID如果无效的话重定向到sso尝试获取登陆状态
            if(uuid == null || !stringRedisTemplate.hasKey(uuid.toString())){
                String callBack = ssoUrl+"check?callBack="+request.getHeader("referer");
                System.out.println("callBack="+callBack);
                response.sendRedirect(callBack);
                return false;
            }

        }
        return super.preHandle(request, response, handler);
    }
}
