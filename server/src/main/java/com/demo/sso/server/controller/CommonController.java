package com.demo.sso.server.controller;

import com.demo.sso.common.util.JsonResult;
import com.demo.sso.server.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2018/9/19.
 *
 * @author wangxiaodong
 */
@Controller
public class CommonController {

    @Resource
    private CommonService commonService;

    /**
     * 登陆界面
     * @return
     */
    @GetMapping(value = "/login")
    public String login(String callBack, Model model){
        model.addAttribute("callBack", callBack);
        return "login";
    }

    /**
     * 检查是否登陆
     * @return 已登录重定向到之前页面，未登录返回登陆页面
     */
    @GetMapping(value = "/check")
    public void check(@CookieValue("uuid")String uuid, String callBack, HttpServletResponse response) throws IOException {
        if(!commonService.check(uuid)){
            response.sendRedirect("/login");
        }else{
            //拼接参数
            callBack +=callBack.indexOf('?') == -1?"?":"&";
            callBack+="uuid="+uuid;
            response.sendRedirect(callBack);
        }
    }

    /**
     * 登陆接口
     * @param username
     * @param password
     * @return 登陆结果
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public JsonResult login(String username, String password, HttpServletResponse response){
        JsonResult jsonResult = commonService.login(username, password);
        if(jsonResult.success){
            Cookie cookie = new Cookie("uuid", jsonResult.getData().toString());
        }
        return jsonResult;
    }

    /**
     * 退出后返回到登陆页面
     * @param response
     * @param uuid
     * @param callBack
     * @throws IOException
     */
    @GetMapping(value = "/logout")
    public void logout(HttpServletResponse response, @CookieValue("uuid")String uuid, String callBack) throws IOException {
        response.addCookie(new Cookie("uuid", null));
        commonService.logout(uuid);
        response.sendRedirect("/login?callBack="+callBack);
    }
}
