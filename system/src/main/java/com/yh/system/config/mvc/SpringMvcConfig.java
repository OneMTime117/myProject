package com.yh.system.config.mvc;

import com.yh.system.config.mvc.converter.CommonEnumConverterFactory;
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
}
