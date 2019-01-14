package com.my.demo.springcloud.service.service;

import com.my.demo.springcloud.mapper.domain.User;

import java.util.List;

/**
 * Created by zhangzhile on 2018/2/23.
 */
public interface IUserApiService {

    boolean addUser(User user)throws Exception;

    List<User> queryList()throws Exception;
}
