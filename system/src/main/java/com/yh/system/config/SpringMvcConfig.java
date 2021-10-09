package com.yh.system.config;

import com.yh.system.config.converter.CommonEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

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
