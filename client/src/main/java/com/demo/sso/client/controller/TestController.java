package com.demo.sso.client.controller;

import com.demo.sso.client.annotation.LoginRequired;
import com.demo.sso.client.service.CommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/9/19.
 *
 * @author wangxiaodong
 */
@Controller
public class TestController {

    @Resource
    private CommonService commonService;
    @Value("server.url")
    private String url;

    @GetMapping(value = "/page1")
    public String page1(String uuid){
        return "page1";
    }

    @GetMapping(value = "/page2")
    @LoginRequired
    public String page2(@CookieValue("uuid")String uuid, Model model){
        model.addAttribute("uuid", uuid);
        return "page2";
    }
}
