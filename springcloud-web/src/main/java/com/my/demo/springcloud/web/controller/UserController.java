package com.my.demo.springcloud.web.controller;

import com.my.demo.springcloud.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangzhile on 2018/2/23.
 */
@RestController
@RefreshScope //动态刷新配置信息
public class UserController {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService us;
    /*@Autowired
    private RestTemplate restTemplate;*/
    @Value("${from}")
    private String fromValue;
    @Value("${userId}")
    private String userId;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Autowired
    private Environment environment;


    @RequestMapping("/user/add")
    public String addUser() {
        //LOGGER.info("restTemplate信息：[{}]",restTemplate.getForEntity("http://USERSERVER/user/add",String.class).getBody());
        LOGGER.info("配置信息 from-value:[{}]",fromValue);
        String property = environment.getProperty("from", "default");
        LOGGER.info("配置信息 getProperty:[{}]",property);
        LOGGER.info("配置信息 overrides userId:[{}]",userId);
        LOGGER.info("配置信息 jdbc-url:[{}]",jdbcUrl);
        return us.addUser();
    }

    @RequestMapping("/user/{id}")
    public String getUserInfo(@PathVariable("id") String id) {
        return us.getUserInfo(id);
    }
    @RequestMapping("/user")
    public String getUSerList(@RequestParam("id") String id) {
        return us.getUSerList(id);
    }

}

