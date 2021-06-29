package com.yh.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yh.common.demo.jackson.JsonDemoDTO;
import com.yh.common.demo.validator.BeanValidatorDemoDTO;
import com.yh.common.util.json.JacksonUtil;
import com.yh.common.util.spring.MessageUtil;
import com.yh.system.domain.dto.user.QueryUserInfoDTO;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.entity.sys.SysUser;
import com.yh.system.mapper.sys.SysUserMapper;
import com.yh.system.service.sys.SysUserService;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Field;
import java.util.*;

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
	public void system() throws NoSuchMethodException {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		ExecutableValidator executableValidator = validatorFactory.getValidator().forExecutables();

	}


	@Test
	public void setSysUserMapperUpdateTest() {
		BeanValidatorDemoDTO dto = new BeanValidatorDemoDTO();
		dto.setAge(11);
		ArrayList<String> listName = new ArrayList<String>();
		listName.add("");
		dto.setListName(listName);
		dto.setNum(12);
		dto.setIsT(false);
		dto.setSex("3");
		dto.setPhoneNumber("111111111111");


		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
				.configure()
//				.failFast(true)
				.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		Set<ConstraintViolation<BeanValidatorDemoDTO>> validate = validator.validate(dto);
		validate.forEach(action -> {
			System.out.println(action.getPropertyPath());
			System.out.println(action.getMessage());
			System.out.println(action.getInvalidValue());
		});
	}

	@Test
	public void insertSysUserMapperTest() throws JsonProcessingException {
		LambdaQueryWrapper<SysUser> qw = new LambdaQueryWrapper<>();
		qw.inSql(SysUser::getId, "1) or 1=1 or id in (1");
		List<SysUser> sysUsers = sysUserMapper.selectList(qw);
	}


	@Test
	public void Test1() throws IllegalAccessException {
		BeanValidatorDemoDTO dto = new BeanValidatorDemoDTO();
		Field[] declaredFields = dto.getClass().getDeclaredFields();
		for (Field declaredField : declaredFields) {
			declaredField.setAccessible(true);
			System.out.println(declaredField.getName());
			Object o = declaredField.get(dto);
			System.out.println(o);
		}
	}

	@Test
	public void Test() throws JsonProcessingException {
		String [] ids = {"03e3e28d8636b348c61f4a0ac53a15c7","06def0d55a80c70a288bfc91b5c37faa"};
		ArrayList<String> list = new ArrayList<>();
		list.addAll(Arrays.asList(ids));
		LambdaUpdateWrapper<SysUser> uw = new LambdaUpdateWrapper<SysUser>()
				.set(SysUser::getNickName, "hahha")
				.in(SysUser::getId, list);
		sysUserMapper.update(null,uw);
	}
}
