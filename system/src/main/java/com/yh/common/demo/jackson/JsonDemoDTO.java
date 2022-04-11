package com.yh.common.demo.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@ToString
@Data
public class JsonDemoDTO {
	private String id;
	private String name;

	private static final String oldName ="1";

	private Integer age;

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonDemoDTO jsonDemoDTO = new JsonDemoDTO();
		String valueAsString = objectMapper.writeValueAsString(jsonDemoDTO);
		System.out.println(valueAsString);

		ArrayList<JsonDemoDTO> jsonDemoDTOS = new ArrayList<>();
		jsonDemoDTOS.add(jsonDemoDTO);

		String valueAsString1 = objectMapper.writeValueAsString(jsonDemoDTOS);
		System.out.println(valueAsString1);
		ArrayList arrayList = objectMapper.readValue(valueAsString1, ArrayList.class);



		String json = "{\"name\":\"YH\",\"id\":\"54321\"}";
		JsonDemoDTO jsonDemoDTO1 = objectMapper.readValue(json, JsonDemoDTO.class);
		System.out.println(jsonDemoDTO1.toString());
	}


}
