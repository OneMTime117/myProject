package com.yh.common.demo.springboot.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator  implements HealthIndicator {

	@Override
	public Health health() {
		return Health.down().withDetail("Error Code","500").build();
	}

}
