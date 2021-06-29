package com.yh.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yh.common.util.spring.SpringBeanUtil;

import java.util.List;

/**
 * @author yuanhuan
 * @description: 基于使用springMVC整合Jackson所配置的ObjectMapper，来实现Bean-jsonStr，List-jsonArrayStr的相互转化
 * @date 2021/6/5 16:19
 */

public class JacksonUtil {

	//获取spring容器中，注册的objectMapper
	private static final ObjectMapper OBJECT_MAPPER = SpringBeanUtil.getBean(ObjectMapper.class);

	/**
	 * 将对象转换为jsonStr
	 */
	public static String toJsonStr(Object object) throws JsonProcessingException {
		return OBJECT_MAPPER.writeValueAsString(object);
	}

	/**
	 * 将jsonStr转换为对象
	 */
	public static <T> T toBean(String jsonStr, Class<T> clazz) throws JsonProcessingException {
		return OBJECT_MAPPER.readValue(jsonStr, clazz);
	}

	/**
	 * 将jsonStr转换为List<T>
	 */
	public static <T> List<T> toList(String jsonArrayStr, Class<T> clazz) throws JsonProcessingException {
		JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
		return OBJECT_MAPPER.readValue(jsonArrayStr, javaType);
	}

	//将List<T>转换为jsonStr
	public static String toJsonArrayStr(List list) throws JsonProcessingException {
		return OBJECT_MAPPER.writeValueAsString(list);
	}

}
