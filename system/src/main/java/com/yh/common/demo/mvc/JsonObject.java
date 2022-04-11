package com.yh.common.demo.mvc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonObject {

	private String name;

	private Integer age;

	public static class Serializer extends JsonSerializer<JsonObject> {
		@Override
		public void serialize(JsonObject value, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
			jgen.writeStartObject();
			jgen.writeStringField("name", value.getName());
			jgen.writeNumberField("age", value.getAge());
			jgen.writeEndObject();
		}
	}

	public static class Deserializer extends JsonDeserializer<JsonObject> {
		@Override
		public JsonObject deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			ObjectCodec codec = jsonParser.getCodec();
			JsonNode tree = codec.readTree(jsonParser);
			String name = tree.get("name").textValue();
			int age = tree.get("age").intValue();
			return new JsonObject(name, age);
		}
	}
}
