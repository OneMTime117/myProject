package com.yh.system.config.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;

@Slf4j
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

//	@Bean
//	public ThreadPoolTaskScheduler taskScheduler(TaskSchedulerBuilder builder) {
//		return builder.build();
//	}
//
//	@Bean
//	public TaskSchedulerBuilder taskSchedulerBuilder(TaskSchedulingProperties properties,
//	                                                 ObjectProvider<TaskSchedulerCustomizer> taskSchedulerCustomizers) {
//		TaskSchedulerBuilder builder = new TaskSchedulerBuilder();
//		builder = builder.poolSize(properties.getPool().getSize());
//		TaskSchedulingProperties.Shutdown shutdown = properties.getShutdown();
//		builder = builder.awaitTermination(shutdown.isAwaitTermination());
//		builder = builder.awaitTerminationPeriod(shutdown.getAwaitTerminationPeriod());
//		builder = builder.threadNamePrefix(properties.getThreadNamePrefix());
//		builder = builder.customizers(taskSchedulerCustomizers);
//		return builder;
//	}

	@Bean
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				log.warn(method.getName()+"    "+ex.getMessage()+"    "+params.toString());
			}
		};
	}
}
