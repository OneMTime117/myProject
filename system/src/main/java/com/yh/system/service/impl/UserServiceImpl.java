package com.yh.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.yh.system.mapper.UserMapper;
import com.yh.system.service.UserService;
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

}
