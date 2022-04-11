package com.yh.system.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {

	@Async
	@Scheduled(cron="*/5 * * ? * 3")
	public void task() {
		log.info("test任务-开始执行");
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("test任务-执行完成");
	}

	public static void main(String[] args) {
		SimpleKeyGenerator simpleKeyGenerator = new SimpleKeyGenerator();
		Object o = simpleKeyGenerator.generateKey("1");
		System.out.println(o.toString());
	}
}
