package com.yh.common.demo.springboot.tx;

import com.yh.system.domain.entity.sys.SysUser;
import com.yh.system.mapper.sys.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class TxTwoServiceImpl {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private TxOneServiceImpl txOneService;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void update() throws IOException {

		SysUser sysUser = new SysUser();
		sysUser.setUsername("11112");
		sysUser.setPassword("1");
		sysUserMapper.insert(sysUser);
		throw new IOException();
	}
}
