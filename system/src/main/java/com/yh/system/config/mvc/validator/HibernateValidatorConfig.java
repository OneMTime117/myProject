package com.yh.system.config.mvc.validator;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.LocaleContextMessageInterpolator;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanhuan
 * @description: springMVC整合hibernateValidator（springBoot提供默认配置）
 * @date 2021/6/5 16:27
 */

@Configuration
public class HibernateValidatorConfig {

	//国际化消息资源配置(springboot必须指定BeanName为messageSource，这样自动配置的Bean才会失效)
	@Bean(name="messageSource")
	public ResourceBundleMessageSource resourceBundleMessageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("message.ValidationMessages","org.hibernate.validator.ValidationMessages");
		messageSource.setCacheMillis(3);//资源文件缓存时间 3分钟
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

//  定义Bean约束校验的校验器（用于支持hibernateValidator的Bean约束）
	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean(){
		LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
		//使用hibernateValidator作为校验器
		factoryBean.setProviderClass(HibernateValidator.class);
		//引入自定义国际资源，作为校验错误消息
		factoryBean.setValidationMessageSource(resourceBundleMessageSource());

		//定义Validator属性（快速失败，默认false）
		HashMap<String, String> validationProperty = new HashMap<>();
		validationProperty.put("hibernate.validator.fail_fast","false");
		factoryBean.setValidationPropertyMap(validationProperty);
		return factoryBean;
	}

//	定义方法校验的后置处理，指定使用的校验处理器（用于支持hibernateValidator的方法约束）
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor(){
		LocalValidatorFactoryBean localValidatorFactoryBean = localValidatorFactoryBean();
		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
		methodValidationPostProcessor.setValidatorFactory(localValidatorFactoryBean());
		return methodValidationPostProcessor;
	}
}
