package com.yh.common.demo.ioc;

import com.yh.system.domain.dto.user.UserInfoDTO;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<UserInfoDTO> {

	@Override
	public UserInfoDTO getObject() throws Exception {
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		return userInfoDTO;
	}

	@Override
	public Class<?> getObjectType() {
		return UserInfoDTO.class;
	}
}
