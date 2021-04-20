package com.yh.system.config.mvc;

import com.baomidou.mybatisplus.annotation.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Objects;

public class CommonEnumConverterFactory implements ConverterFactory<String, IEnum<String>> {

	@Override
	public <T extends IEnum<String>> Converter<String, T> getConverter(Class<T> targetType) {
		return new CommonEnumConverter<>(targetType);
	}

	public class CommonEnumConverter<T extends IEnum<String>> implements Converter<String, T> {

		private final T[] values;

		public CommonEnumConverter(Class<T> targetType) {
			//获取当前枚举类型的所有枚举值
			values = targetType.getEnumConstants();
		}

		@Override
		public T convert(String source) {
			for (T t : values) {
				if (Objects.equals(source, t.getValue())) {
					return t;
				}
			}
			return null;
		}
	}

}
