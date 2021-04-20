package com.yh.system.config.mvc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		//添加mybatisPlus分页插件，通过mapper层代码里的拦截器实现
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
		paginationInnerInterceptor.setMaxLimit(500l);//null,每页查询最大值,防止恶意传参，size非常大
		paginationInnerInterceptor.setOverflow(true);//默认false,当前页大于最大页时，返回第一页数据
		paginationInnerInterceptor.setOptimizeJoin(true);//默认true,leftJoin优化（特殊情况下，局部进行关闭）
		mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
		return mybatisPlusInterceptor;
	}

//	//注入自定义的id生成器,一般情况下，使用默认的雪花算法和UUID就可以
//	@Bean
//	public IdentifierGenerator identifierGenerator(){
//		return new MybatisPlusIdGenerator();
//	}
}
