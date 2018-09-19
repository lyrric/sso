package com.demo.sso.client.service.impl;

import com.demo.sso.client.service.CommonService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public boolean checkLogin(String uuid) {
        return uuid != null && stringRedisTemplate.boundValueOps(uuid).persist();
    }
}
