package com.yh.system.service.impl;

import com.yh.system.mapper.UserMapper;
import com.yh.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public void mybatisCommonMethods() {

    }
}
