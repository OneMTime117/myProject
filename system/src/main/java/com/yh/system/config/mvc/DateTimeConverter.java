package com.yh.system.config.mvc;

import cn.hutool.core.date.DateUtil;
import com.yh.system.domain.enums.SexEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

@Configuration
public class DateTimeConverter {

	@Bean
	public Converter<String, Date> DateConverter() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				//使用Hutool-DateUtil工具类，支持多种时间类型的格式化
				return DateUtil.parse(source);
			}
		};
	}

	@Bean
	public Converter<String, LocalDateTime> localDateTimeConverter() {
		return new Converter<String, LocalDateTime>() {
			@Override
			public LocalDateTime convert(String source) {
				return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			}
		};
	}

	@Bean
	public Converter<String, LocalDate> localDateConverter() {
		return new Converter<String, LocalDate>() {
			@Override
			public LocalDate convert(String source) {
				return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
		};
	}

	@Bean
	public Converter<String, LocalTime> localTimeConverter() {
		return new Converter<String, LocalTime>() {
			@Override
			public LocalTime convert(String source) {
				return LocalTime.parse(source, DateTimeFormatter.ofPattern("HH:mm:ss"));
			}
		};
	}

	//	@Bean
	public Converter<String, SexEnum> enumConverTer() {
		return new Converter<String, SexEnum>() {
			@Override
			public SexEnum convert(String source) {
				SexEnum[] values = SexEnum.values();
				for (SexEnum value : values) {
					if (Objects.equals(source, value.getValue())) {
						return value;
					}
				}
				return null;
			}
		};
	}
}
