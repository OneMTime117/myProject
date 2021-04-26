package com.yh.system.config.mvc.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author yuanhuan
 * @description: springfox-swagger2配置类，接口访问路径为：ip:port/xx/v2/api-docs
 * 如果引入springfox-swagger-UI,其接口UI地址为: ip:port/xx/swagger-ui.html
 * 如果引入knife4j,其接口UI地址为: ip:port/xx/doc.html
 * @date 2021/4/26 9:11
 */

@Configuration
@EnableSwagger2WebMvc//开启swagger2功能
public class swaggerConfig {

	//自定义配置参数，用于外部控制swagger插件的开启
	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;

	@Bean
	public Docket docket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				//是否开启
				.enable(swaggerEnabled)
				//接口文档基本信息
				.apiInfo(apiInfo())
				//如果设置了多个docket实例，则需要指定分组名，默认为DEFAULT_GROUP_NAME
//				.groupName(null)
				//初始化api选择器的构建器
				.select()
				//需要扫描的api接口（默认any）
				.apis(RequestHandlerSelectors.basePackage("com.yh"))
				//需要暴露的接口（默认any）
				.paths(PathSelectors.any())
				//构建选择器
				.build()
				//接口文档前缀basePath，需要和server.servlet.content-path保持一致
				.pathMapping("/");
		return docket;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//接口文档标题
				.title("Api Document for myProject-system")
				//接口文档描述
				.description("swagger文档")
				//接口联系人
				.contact(new Contact("yh", "/", "**"))
				//版本
				.version("1.0.0v")
				.build();
	}

}
