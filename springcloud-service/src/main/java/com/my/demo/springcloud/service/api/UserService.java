package com.my.demo.springcloud.service.api;

import com.my.demo.springcloud.service.bean.User;
import com.my.demo.springcloud.service.service.IUserApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by zhangzhile on 2018/2/23.
 */
@RestController
public class UserService {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private IUserApiService userApiService;

    // PathVariable
    @RequestMapping("/user/add")
    public boolean addUser() throws Exception{
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        LOGGER.info("/user/add,host:{},service_id:{}",localServiceInstance.getHost(),localServiceInstance.getServiceId());
        com.my.demo.springcloud.mapper.domain.User user=new com.my.demo.springcloud.mapper.domain.User();
        user.setId(UUID.randomUUID().toString().substring(0,20));
        user.setUserage("123");
        user.setUserName("zhangsan");
        user.setUserPassword("password");
        return userApiService.addUser(user);
    }

    // PathVariable
    @RequestMapping("/user/{id}")
    public User getUserInfo(@PathVariable("id") String id) {
        User bean = new User();
        bean.setAge("Orange");
        bean.setName("lili");
        bean.setId(id);
        return bean;
    }
    // Path
    @RequestMapping
    public User getUserList(@RequestParam(required = true, value = "id") String id) {
        User bean = new User();
        bean.setAge("Banana");
        bean.setName("Mr Wang");
        bean.setId(id);
        return bean;
    }
}
