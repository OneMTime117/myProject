package com.yh.system.config.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncThreadPoolConfig {

	//jvm核心线程数
	private final Integer coreThreadNum = Runtime.getRuntime().availableProcessors();

	@Bean
	public ThreadPoolTaskExecutor asyncTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();//springboot自带的线程池对象,推荐使用
		executor.setCorePoolSize(coreThreadNum);//最小线程数
		executor.setMaxPoolSize(coreThreadNum * 2 + 1);//最大线程数
		executor.setQueueCapacity(coreThreadNum);//线程池任务队列容量
		executor.setThreadNamePrefix("异步线程-");//该线程池中的线程name的前缀
		executor.initialize();//初始化自定义线程池
		return executor;
	}
}
