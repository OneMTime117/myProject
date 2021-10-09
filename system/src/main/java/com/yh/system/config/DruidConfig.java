package com.yh.system.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid sql监控器配置
 * 监控器地址： ${ip}:${port}/${applicationName}/druid
 * */

@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(statViewServlet,"/druid/*");
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webStatFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
