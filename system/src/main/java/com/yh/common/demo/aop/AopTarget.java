package com.yh.common.demo.aop;


import com.yh.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AopTarget {

	public Map<String, String> getName(String fixed) throws Exception{
		System.out.println("目标方法执行中");
		Map<String,String> map  = new HashMap<>();
		map.put("name",fixed+"Name");
		if (Objects.equals(fixed,"1")){
			throw new BusinessException("");
		}else {
			return map;
		}
	}
}
