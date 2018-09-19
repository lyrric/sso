package com.demo.sso.client.annotation;

import java.lang.annotation.*;

/**
 * Created on 2018/9/19.
 * 登陆注解，配置此注解时表示该方法需要登陆
 * @author wangxiaodong
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
}
