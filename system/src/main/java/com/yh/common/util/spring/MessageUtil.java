package com.yh.common.util.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author yuanhuan
 * @description: 获取国际化资源工具类
 * 基于spring所提供的MessageSource工具类，来获取加载resource目录下的资源
 * @date 2021/5/18 9:18
 */

public class MessageUtil {
	/**
	 * 根据消息键和参数 获取消息 委托给spring messageSource
	 *
	 * @param code 消息键
	 * @param args 参数
	 * @return 获取国际化翻译值
	 */
	public static String message(String code, Object... args) {
		MessageSource messageSource = SpringBeanUtil.getBean(MessageSource.class);
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}
}

