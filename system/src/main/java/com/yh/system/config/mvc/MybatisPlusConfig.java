package com.yh.system.config.mvc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.yh.system.config.mvc.sqlinjector.MySqlInjector;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MybatisPlusConfig {

	//添加mybatisPlus内置插件（拦截器）
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		//分页插件
		PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
		paginationInnerInterceptor.setMaxLimit(500L);//null,每页查询最大值,防止恶意传参，size非常大
		paginationInnerInterceptor.setOverflow(true);//默认false,当前页大于最大页时，返回第一页数据
		paginationInnerInterceptor.setOptimizeJoin(true);//默认true,leftJoin优化（特殊情况下，局部进行关闭）
		mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

		//防止全表更新与删除插件
		BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
		mybatisPlusInterceptor.addInnerInterceptor(blockAttackInnerInterceptor);
		return mybatisPlusInterceptor;
	}

	//添加mybatisPlus自定义sql注入器（由于sql注入器只能有一个，因此需要继承DefaultSqlInjector,保证原有mapperCRUD方法的sql注入）
	@Bean
	public ISqlInjector iSqlInjector() {
		return new MySqlInjector();
	}

	//mybatis Configuration配置
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return configuration -> {
			//添加mybatis自定义拦截器
//			configuration.addInterceptor(new PageMybatisInterceptor());
//			configuration.addInterceptor(new DataAuthMybatisInterceptor());
		};
	}

	//mybatisPlus填充处理器，填充BaseEntity中的字段
	@Bean
	public MetaObjectHandler metaObjectHandler() {
		return new MetaObjectHandler() {
			@Override
			public void insertFill(MetaObject metaObject) {
				//新增时，添加createdDate，createdBy字段数据
				this.strictInsertFill(metaObject, "createdDate", LocalDateTime.class, LocalDateTime.now());
			}

			@Override
			public void updateFill(MetaObject metaObject) {
				//修改时，添加lastModifiedDate、lastModifiedBy字段数据
				this.strictUpdateFill(metaObject, "lastModifiedDate", LocalDateTime.class, LocalDateTime.now());
			}
		};
	}

//	//注入自定义的id生成器,一般情况下，使用默认的雪花算法和UUID就可以
//	@Bean
//	public IdentifierGenerator identifierGenerator(){
//		return new MybatisPlusIdGenerator();
//	}

	//自定义identifierGenerator,id生成器
	public class MybatisPlusIdGenerator implements IdentifierGenerator {
		@Override
		public Number nextId(Object entity) {
			return null;
		}

		@Override
		public String nextUUID(Object entity) {
			return null;
		}
	}
}
