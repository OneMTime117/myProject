package com.yh.common.util;

import com.yh.common.constant.RedisKeyConstant;
import com.yh.common.exception.AuthenticationAccessException;
import com.yh.common.exception.BusinessException;
import com.yh.common.util.redis.RedisUtil;
import com.yh.common.util.web.WebServletUtil;
import com.yh.system.domain.dto.user.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;



@Component
public class UserInfoUtil {

    @Autowired
    private RedisUtil redisUtil;

    public UserInfoDTO getUserInfo() {
        HttpServletRequest request = WebServletUtil.getRequest();
        String token = request.getHeader("token");
        UserInfoDTO userInfoDTO;
        try {
            userInfoDTO = redisUtil.getObject(RedisKeyConstant.TOKEN_PREFIX + token, UserInfoDTO.class);
        } catch (IOException e) {
            throw new BusinessException("用户数据反序列化错误");
        }
        if (userInfoDTO == null) {
            throw new AuthenticationAccessException("token失效，请重新登录");
        }
        return userInfoDTO;
    }


    public UserInfoDTO getUserInfoOfUrl() {
        HttpServletRequest request = WebServletUtil.getRequest();
        String token = request.getParameter("token");
        UserInfoDTO userInfoDTO;
        try {
            userInfoDTO = redisUtil.getObject(RedisKeyConstant.TOKEN_PREFIX + token, UserInfoDTO.class);
        } catch (IOException e) {
            throw new BusinessException("用户数据反序列化错误");
        }
        if (userInfoDTO == null) {
            throw new AuthenticationAccessException("token失效，请重新登录");
        }
        return userInfoDTO;
    }
}
