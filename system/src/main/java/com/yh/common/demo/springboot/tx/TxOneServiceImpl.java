package com.yh.common.demo.springboot.tx;

import com.yh.system.mapper.sys.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service
public class TxOneServiceImpl {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private PlatformTransactionManager transactionManager;

	public void update() {
	}
}
