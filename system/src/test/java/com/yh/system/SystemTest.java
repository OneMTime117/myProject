package com.yh.system;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yh.system.domain.entity.sys.SysUser;
import com.yh.system.mapper.sys.SysUserMapper;
import com.yh.system.service.sys.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author yuanhuan
 * @description:
 * @date 2021/3/21 18:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemTest {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserService sysUserService;

	@Test
	public void sysUserMapperTest() {
		String s = IdUtil.simpleUUID();
		System.out.println(s);
	}

	@Test
	public void sysUserMapperQueryTest() {
		LambdaQueryWrapper<SysUser> eq = new LambdaQueryWrapper<SysUser>().select(SysUser::getBirthday)
				.eq(SysUser::getUsername, "yh");
		Map<String, Object> map = sysUserService.getMap(eq);
	}

	@Test
	public void setSysUserMapperUpdateTest() {
		LambdaUpdateWrapper<SysUser> sw = new LambdaUpdateWrapper<>();
		SysUser sysUser = new SysUser();
		sysUser.setId("3");
		sysUser.setUsername("yh");
		sysUser.setPassword("1233444");
		sysUserService.saveOrUpdate(sysUser, sw);
	}
}
