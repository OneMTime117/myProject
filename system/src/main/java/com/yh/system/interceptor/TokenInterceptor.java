package com.yh.system.interceptor;

import cn.hutool.core.util.StrUtil;
import com.yh.common.constant.RedisKeyConstant;
import com.yh.common.exception.AuthenticationAccessException;
import com.yh.common.util.redis.RedisUtil;
import com.yh.system.domain.dto.user.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    public static final String TOKEN_HEADER = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TOKEN_HEADER);
        if (StrUtil.isBlank(token)) {
            throw new AuthenticationAccessException("token失效，请重新登录");
        }
        UserInfoDTO userInfoDTO = redisUtil.getObject(RedisKeyConstant.TOKEN_PREFIX + token, UserInfoDTO.class);
        if (userInfoDTO == null) {
            throw new AuthenticationAccessException("token失效，请重新登录");
        }
        //刷新token有效期
        redisUtil.expireObject(RedisKeyConstant.TOKEN_PREFIX + token, 3 * 60 * 60);

        //todo 对数据权限进行校验，可以在handle上添加注解实现制定那些角色访问
        return true;
    }
}
