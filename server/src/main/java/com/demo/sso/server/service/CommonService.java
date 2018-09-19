package com.demo.sso.server.service;

import com.demo.sso.common.util.JsonResult;

import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/9/19.
 *
 * @author wangxiaodong
 */
public interface CommonService {


    JsonResult login(String username, String password);

    boolean check(String uuid);

    void logout(String uuid);
}
