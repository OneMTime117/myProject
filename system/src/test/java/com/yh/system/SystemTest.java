package com.yh.system;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yh.common.demo.aop.AopTarget;
import com.yh.common.demo.cache.CacheServiceImpl;
import com.yh.common.demo.email.EmailDemo;
import com.yh.common.util.UserInfoUtil;
import com.yh.common.util.redis.RedisUtil;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.mapper.sys.SysRoleMapper;
import com.yh.system.mapper.sys.SysUserMapper;
import com.yh.system.mapper.sys.SysUserRoleMapper;
import com.yh.system.service.sys.SysUserRoleService;
import com.yh.system.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuanhuan
 * @description:
 * @date 2021/3/21 18:38
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemTest {

	@Autowired
	private static UserInfoUtil userInfoUtil;

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private UserInfoDTO userInfoDTO;
	@Autowired
	private AopTarget aopTarget;

	@Test
	public void iocTest() throws Exception {
		Map<String, String> name = aopTarget.getName("2");
		name.forEach((k, v) -> {
			System.out.println(k + ":" + v);
		});
	}

	@Autowired
	private EmailDemo emailDemo;

	@Test
	public void testAA() throws MessagingException {
		emailDemo.sendSimpleMail("Test", "生日快乐", "yuanhuanpeipei@gmail.com");
	}


	@Test
	public void testA() {
		Objects.requireNonNull(null, "sss");
	}

	@Autowired
	private CacheServiceImpl cacheService;

	@Test
	public void testCache() throws IOException {
		long test = cacheService.test(false);
		long test1 = cacheService.test(false);

		long test2 = cacheService.test(true);
		long test3 =cacheService.test(true);




	}

}
