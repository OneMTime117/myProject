package com.yh.system.config.mvc.converter;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.yh.common.exception.BusinessException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Objects;

/**
 * @author yuanhuan
 * @description: 通用枚举转换器工厂，搭配IEnum接口使用
 * @date 2021/4/21 16:59
 */

public class CommonEnumConverterFactory implements ConverterFactory<String, IEnum<?>> {

	@Override
	public <T extends IEnum<?>> Converter<String, T> getConverter(Class<T> targetType) {
		return new CommonEnumConverter<>(targetType);
	}

	public class CommonEnumConverter<T extends IEnum<?>> implements Converter<String, T> {

		private final T[] values;

		public CommonEnumConverter(Class<T> targetType) {
			//获取当前枚举类型的所有枚举值
			values = targetType.getEnumConstants();
		}

		@Override
		public T convert(String source) {
			if (StrUtil.isBlank(source.trim())) {
				return null;
			}
			for (T t : values) {
				if (Objects.equals(source, t.getValue())) {
					return t;
				}
			}
			throw new BusinessException("枚举类型转换失败");
		}
	}
}
