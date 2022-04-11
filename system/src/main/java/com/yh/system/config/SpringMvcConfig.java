package com.yh.system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yh.system.config.converter.CommonEnumConverterFactory;
import com.yh.system.interceptor.ApiAccessLogInterceptor;
import com.yh.system.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

	private static final String[] EXCLUDE_PATH = {
			//swagger
			"/v2/**",
			"/swagger-resources/**",
			"/webjars/**",
			"/doc.html/**",
			"/*.ico",
			"/error",
			//登录接口
			"/user/login",
			//websocket接口
			"/webSocket/**",
			//actuator端口
			"/actuator/**"
	};
	@Autowired
	private TokenInterceptor tokenInterceptor;
	@Autowired
	private ApiAccessLogInterceptor apiAccessLogInterceptor;

	@CrossOrigin
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		token登录
		InterceptorRegistration tokenRegistration = registry.addInterceptor(tokenInterceptor);
		tokenRegistration.addPathPatterns("/**");
		tokenRegistration.excludePathPatterns(EXCLUDE_PATH);
//		//日志记录
//		InterceptorRegistration apiLogRegistration = registry.addInterceptor(apiAccessLogInterceptor);
//		apiLogRegistration.addPathPatterns("/**");

	}

	//http消息转化器
	@Bean
	MappingJackson2HttpMessageConverter httpMessageConverter(@Qualifier("objectMapper") ObjectMapper objectMapper) throws ParseException {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper);
		return converter;
	}

	//注册格式化器、转换器工厂
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverterFactory(new CommonEnumConverterFactory());
	}



	/**
	 * 内置cors配置
	 * 其本身使用springMVC拦截器实现,并且优先级最低
	 * 从而导致开发者自定义的权限拦截器,提供拦截跨域的预检请求,服务器无法完成跨域处理
	 * 因此推荐使用原生CorsFilter,提供跨域处理的优先级
	 * */
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedOrigins("*")
//				.allowedHeaders("*")
//				.allowedMethods("*")
//				/**
//				 * 允许请求发送cookie等安全证书
//				 * 当allowedOrigins为*时，就不能设置allCrendentials为true（springboot高版本中，取消了该校验）
//				 */
//				.allowCredentials(true)
//				.maxAge(3600);//预检请求有效期 单位s
//	}
}
