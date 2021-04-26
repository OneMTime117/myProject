package com.yh.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.yh")//保证common包下的Bean被扫描
@MapperScan(basePackages = {"com.yh.system.mapper"})
@EnableTransactionManagement
public class SystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

}
