package com.demo.sso.server.service.impl;

import com.demo.sso.common.util.JsonResult;
import com.demo.sso.server.service.CommonService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created on 2018/9/19.
 *
 * @author wangxiaodong
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public JsonResult login(String username, String password) {
        if("admin".equals(username) && "123456".equals(password)){
            String uuid = UUID.randomUUID().toString();
            stringRedisTemplate.boundValueOps(uuid).set(username);
            return JsonResult.ok(uuid);
        }
        return JsonResult.fail(400, "用户名或密码错误");
    }

    @Override
    public boolean check(String uuid) {
        return uuid != null && stringRedisTemplate.boundValueOps(uuid).persist();
    }

    @Override
    public void logout(String uuid) {
        if(uuid != null){
            stringRedisTemplate.delete(uuid);
        }
    }


}
