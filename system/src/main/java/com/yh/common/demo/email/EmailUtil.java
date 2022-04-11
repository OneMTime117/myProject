package com.yh.common.demo.email;


import com.yh.common.exception.BusinessException;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;


@Import(MailProperties.class)
@Configuration
public class EmailUtil extends JavaMailSenderImpl {

	// 保存多个用户名和密码的队列（spring.mail.username=、
	// spring.mail.password=中定义多个客户端账号、密码，使用","分隔）
	private ArrayList<String> usernameList;
	private ArrayList<String> passwordList;
	// 轮询标识
	private int currentMailId = 0;


	public EmailUtil(MailProperties properties){
		applyProperties(properties);
		//获取所有账号
		usernameList=new ArrayList<>(Arrays.asList(properties.getUsername().split(",")));
		passwordList=new ArrayList<>(Arrays.asList(properties.getPassword().split(",")));
		if (usernameList.size()!=passwordList.size()){
			throw new BusinessException("EmailUtil创建失败,请检查相关配置");
		}
	}

	@Override
	protected void doSend(MimeMessage[] mimeMessages, Object[] originalMessages) throws MailException {
		int size = usernameList.size();
		//轮询
		currentMailId = (currentMailId + 1) % size;
		this.setUsername(usernameList.get(currentMailId));
		this.setPassword(passwordList.get(currentMailId));
		super.doSend(mimeMessages, originalMessages);
	}

	@Override
	public String getUsername() {
		return usernameList.get(currentMailId);
	}

	private void applyProperties(MailProperties properties) {
		this.setHost(properties.getHost());
		if (properties.getPort() != null) {
			this.setPort(properties.getPort());
		}
		this.setProtocol(properties.getProtocol());
		if (properties.getDefaultEncoding() != null) {
			this.setDefaultEncoding(properties.getDefaultEncoding().name());
		}
		if (!properties.getProperties().isEmpty()) {
			this.setJavaMailProperties(asProperties(properties.getProperties()));
		}
	}

	private Properties asProperties(Map<String, String> source) {
		Properties properties = new Properties();
		properties.putAll(source);
		return properties;
	}

}
