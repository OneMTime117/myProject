package com.yh.common.demo.mvc;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

public class MvcUtil {

	//uri构建
	public  static String buildUri1(){
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("localhost")
				.port(8080)
				.path("/user/login")
				.queryParam("username", "yuanhuan")
				.queryParam("passwrod", "123456")
				.encode()
				.build();
		return uriComponents.expand().toUriString();
	}

	public  static String buildUri2(){
		UriComponents uriComponents = UriComponentsBuilder
				.fromHttpUrl("https://localhost:8080/user/login")
				.queryParam("username", "{a}")
				.queryParam("passwrod", "{b}")
//				.encode()
				.build();
		return uriComponents.expand("袁欢;","2").encode().toUriString();
	}

	public static String uriBuild(){
		DefaultUriBuilderFactory urlBuilderFactory = new DefaultUriBuilderFactory("http://localhost:8080/user/login");
		urlBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);
		UriBuilder builder = urlBuilderFactory.builder();
		return builder.queryParam("name","袁欢").build().toString();
	}

	public static String rquestBuild(HttpServletRequest request){
		UriComponents build = ServletUriComponentsBuilder.fromRequestUri(request)
				.build();

		DefaultUriBuilderFactory urlBuilderFactory = new DefaultUriBuilderFactory("http://localhost:8080/user/login");
		urlBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);
		UriBuilder builder = urlBuilderFactory.builder();
		return builder.queryParam("name","袁欢").build().toString();
	}

	public static void main(String[] args) {
		String s = buildUri2();
		System.out.println(s);
	}
}
