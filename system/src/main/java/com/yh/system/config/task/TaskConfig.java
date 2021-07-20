package com.yh.system.config.task;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConditionalOnProperty(prefix = "scheduling", name = "enabled", havingValue = "true")
@EnableScheduling
@EnableAsync
@Configuration
public class TaskConfig {
}
