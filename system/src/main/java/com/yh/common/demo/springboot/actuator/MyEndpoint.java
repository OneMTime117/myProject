package com.yh.common.demo.springboot.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id ="test")
public class MyEndpoint {

	@ReadOperation
	public String getData(){
		return "sdfsf";
	}
}
