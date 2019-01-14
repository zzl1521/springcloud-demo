package com.my.demo.springcloud.web.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhangzhile on 2018/2/23.
 */
@FeignClient("springcloud-service")
public interface UserService {

    @RequestMapping("/user/add")
    public String addUser();

    @RequestMapping("/user/{id}")
    public String getUserInfo(@PathVariable("id") String id);

    @RequestMapping("/user")
    public String getUSerList(@RequestParam("id") String id);
}
