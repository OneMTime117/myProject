package com.yh.common.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTemplateDemo {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void test(){

	}
}
