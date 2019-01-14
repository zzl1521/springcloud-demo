package com.my.demo.springcloud.mapper.service;

import com.my.demo.springcloud.mapper.domain.User;

/**
 * Created by zhangzhile on 2018/2/23.
 */
public interface IUserService {

    int insertSelective(User record);

    User selectByPrimaryKey(String id);
}
