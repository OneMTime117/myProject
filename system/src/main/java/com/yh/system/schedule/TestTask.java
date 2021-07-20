package com.yh.system.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {

	@Scheduled(initialDelay = 1000 * 60, fixedDelay = 1000 * 60 * 5)
	public void task() {
		log.info("test任务-开始执行");
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("test任务-执行完成");
	}
}
