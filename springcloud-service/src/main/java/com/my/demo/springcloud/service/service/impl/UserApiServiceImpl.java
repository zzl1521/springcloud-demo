package com.my.demo.springcloud.service.service.impl;

import com.my.demo.springcloud.mapper.domain.User;
import com.my.demo.springcloud.mapper.service.IUserService;
import com.my.demo.springcloud.service.service.IUserApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhile on 2018/2/23.
 */
@Service
@RefreshScope //动态刷新配置信息
public class UserApiServiceImpl implements IUserApiService{

    private static final Logger LOGGER= LoggerFactory.getLogger(UserApiServiceImpl.class);

    @Autowired
    private IUserService userService;

    @Value("${from}")
    private String fromValue;
    @Autowired
    private Environment environment;
    /*@Value("${redis.url}")
    private String redisUrl;
    @Value("${jdbc.url}")
    private String jdbcUrl;*/
    @Value("${test.url}")
    private String testUrl;

    public boolean addUser(User user) throws Exception {
        LOGGER.info("配置信息 from-value:[{}]",fromValue);
        String property = environment.getProperty("from", "default");
        LOGGER.info("配置信息 getProperty:[{}]",property);
        /*LOGGER.info("配置信息 redisUrl:[{}]",redisUrl);
        LOGGER.info("配置信息 jdbcUrl:[{}]",jdbcUrl);*/
        LOGGER.info("配置信息 testUrl:[{}]",testUrl);
        if (userService.insertSelective(user)>0){
            return true;
        }
        return false;
    }

    public List<User> queryList() throws Exception {
        return null;
    }
}
