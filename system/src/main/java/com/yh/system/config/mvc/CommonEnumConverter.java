//package com.yh.system.config.mvc;
//
//import com.baomidou.mybatisplus.annotation.IEnum;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//
//import java.util.Objects;
//
//public class CommonEnumConverter<T extends IEnum> implements Converter<String, T> {
//
//	private final T[] values;
//
//	public CommonEnumConverter(Class<T> targetType) {
//		//获取当前枚举类型的所有枚举值
//		values = targetType.getEnumConstants();
//	}
//
//	@Override
//	public T convert(String source) {
//		for (T t : values) {
//			if (Objects.equals(source,t.getValue())) {
//				return t;
//			}
//		}
//		return null;
//	}
//}
