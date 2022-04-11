package com.yh.system.config;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

	/**
	 * 默认日期时间格式
	 */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 默认日期格式
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 默认时间格式
	 */
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		//Date类型序列化与反序列化处理
		configureDate(objectMapper);
		//JDK8的日期时间类型的序列化与反序列化处理
		registerJavaTimeModule(objectMapper);

		//post请求，json数据，对String类型进行trim处理
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(String.class, new JsonDeserializer<String>() {
			@Override
			public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
				return StrUtil.trim(jsonParser.getValueAsString());
			}
		});
		objectMapper.registerModule(simpleModule);

		//反序列化,允许未知字段不进行解析
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		//枚举序列化使用toString方法
		objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		return objectMapper;
	}

	//Date类型序列化与反序列化处理
	public static void configureDate(ObjectMapper objectMapper){
		//jackson进行时间格式转换时，会默认当前时间为UTC(即 GMT +00:00),修改为GMT+8:00
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		//指定date类型的序列化与反序列化处理
		objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT));
	}

	//支持JDK8的日期时间类型的序列化与反序列化处理
	public static void registerJavaTimeModule(ObjectMapper objectMapper) {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
		objectMapper.registerModule(javaTimeModule);
	}

}
