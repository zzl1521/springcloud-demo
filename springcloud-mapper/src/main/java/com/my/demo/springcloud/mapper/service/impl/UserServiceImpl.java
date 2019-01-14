package com.my.demo.springcloud.mapper.service.impl;

import com.my.demo.springcloud.mapper.dao.UserMapper;
import com.my.demo.springcloud.mapper.domain.User;
import com.my.demo.springcloud.mapper.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangzhile on 2018/2/23.
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    public User selectByPrimaryKey(String id) {
        return null;
    }
}
