package com.yh.system.interceptor;


import com.yh.common.util.UserInfoUtil;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.entity.sys.SysApiLog;
import com.yh.system.service.sys.SysApiLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class ApiAccessLogInterceptor implements HandlerInterceptor {

    private final String apiControllerNamePrefix = "com.yh.system.controller";

    private final String login_api = "user/login";

    @Autowired
    private UserInfoUtil userInfoUtil;
    @Autowired
    private SysApiLogService sysApiLogService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler==null||!(handler instanceof HandlerMethod)){
            return;
        }
        //获取接口信息  接口描述、接口url、接口调用方式、接口类名
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> controllerClass = handlerMethod.getMethod().getDeclaringClass();
        //接口全类名,过滤非业务接口的请求
        String name = controllerClass.getName();
        if (!name.contains(apiControllerNamePrefix)) {
            return;
        }

        //接口controllerName
        String simpleName = controllerClass.getSimpleName();

        //接口名
        String apiName = "";
        Api apiAnnotation = controllerClass.getAnnotation(Api.class);
        if (apiAnnotation != null) {
            String[] tags = apiAnnotation.tags();
            if (tags != null) {
                apiName = Arrays.stream(tags).collect(Collectors.joining("/"));
            }
        }
        //接口方法描述
        ApiOperation apiOperationAnnotation = handlerMethod.getMethodAnnotation(ApiOperation.class);
        if (apiOperationAnnotation != null) {
            String value = apiOperationAnnotation.value();
            if (value != null) {
                apiName += "-" + value;
            }
        }

        //接口url、调用方式
        String url = request.getRequestURL().toString();
        String method = request.getMethod();

//      获取操作人id
        String userId = "";
        if (request.getServletPath().contains(login_api)) {
            //通过用户登录接口的请求属性中获取
            userId = (String) request.getAttribute("userId");
        } else {
            UserInfoDTO userInfo = userInfoUtil.getUserInfo();
            userId = userInfo.getId();
        }
        SysApiLog sysApiLog = new SysApiLog();
        sysApiLog.setCreatedBy(userId);
        sysApiLog.setCreatedDate(LocalDateTime.now());
        sysApiLog.setApiControllerName(simpleName);
        sysApiLog.setApiName(apiName);
        sysApiLog.setApiUrl(url);
        sysApiLog.setApiMethod(method);
        sysApiLogService.save(sysApiLog);
    }
}
