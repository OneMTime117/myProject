package com.yh.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yh.system.domain.dto.user.QueryUserInfoDTO;
import com.yh.system.domain.dto.user.UserInfoDTO;
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
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void sysUserMapperTest() {
		Page<?> page = new Page();
		page.setCurrent(1);
		page.setSize(1);
		QueryUserInfoDTO queryUserInfoDTO = new QueryUserInfoDTO();
		queryUserInfoDTO.setUsernameSearch("yh");
		IPage<UserInfoDTO> userInfoDTOIPage = sysUserMapper.pageUserInfoDTO(page, queryUserInfoDTO);
		userInfoDTOIPage.getRecords().forEach(action -> {
					System.out.println(action);
				}
		);
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

	@Test
	public void insertSysUserMapperTest() throws JsonProcessingException {
		SysUser sysUser = sysUserMapper.selectById("1");
		String value = sysUser.getSex().getCode();
		String s = objectMapper.writeValueAsString(sysUser);
		System.out.println(s);
	}
}
