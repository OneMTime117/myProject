package com.yh;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;

public class ApplicationMain {

	public static void main(String[] args) throws IOException {
		JsonFactory jsonFactory = new JsonFactory();
		//创建json生成器,并指定json数据输出位置
		StringWriter stringWriter = new StringWriter();
		JsonGenerator generator = jsonFactory.createGenerator(stringWriter);

		//开始进行json生成
		generator.writeStartObject();
		//json键值对
		generator.writeStringField("name", "yh");
		//json数组
		generator.writeFieldName("array");
		generator.writeStartArray();
		generator.writeString("msg1");
		generator.writeString("msg2");
		generator.writeEndArray();
		//json对象
		generator.writeFieldName("object");
		generator.writeStartObject();
		generator.writeNumberField("id", 1234);
		generator.writeStringField("name", "ddd");
		generator.writeEndObject();
		//接受JSON对象生成，将json数据输出(解析为json字符串)
		generator.writeEndObject();
		generator.close();
		System.out.println(stringWriter.toString());


		System.out.println("hello world");
	}
}
