package com.yh.common.demo.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class JsonObjectMapperApiDemo {

	//数据绑定和树模型 api使用
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		//读取json字符串，转换为JavaBean(支持常用集合List、Map)
		String jsonStr = "{\"name\":\"YH\",\"id\":\"12345\"}";
		HashMap hashMap = objectMapper.readValue(jsonStr, HashMap.class);

		//将javaBean解析为json字符串
		String valueStr = objectMapper.writeValueAsString(hashMap);
		System.out.println(valueStr);

		//树模型
		// json字符串转化为jsonNode（json节点，每个节点都有自己对应的值）
		String json = "{\"name\":\"YH\",\"id\":\"54321\"}";
		JsonNode readTree = objectMapper.readTree(json);
		//获取当前树节点下的字段数据
		JsonNode jsonNode = readTree.get("name");
		String asText = jsonNode.asText();
		System.out.println(asText);
	}
}
