package com.yh.common.demo.cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl {


	@CachePut(value = "cacheName", condition = "#update==true")
	public long test(boolean update) {
		long l = System.nanoTime();
		return l;
	}


}
