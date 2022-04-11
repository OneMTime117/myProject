package com.yh.common.demo.servlet;

import lombok.SneakyThrows;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(value = "asyncServlet",asyncSupported=true)
public class AsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//开启异步请求处理
		AsyncContext asyncContext = req.startAsync();
		ServletInputStream inputStream = req.getInputStream();
		inputStream.setReadListener(new ReadListener() {
			@Override
			public void onDataAvailable() throws IOException {
			}

			@Override
			public void onAllDataRead() throws IOException {
				//开始请求异步处理
				asyncContext.start(new Runnable() {
					@SneakyThrows
					@Override
					public void run() {
						//异步处理请求,通过asyncContext获取
						asyncContext.getResponse().getWriter().write("hhh");

						//结束请求异步处理
						asyncContext.complete();
					}
				});
			}

			@Override
			public void onError(Throwable throwable) {
				asyncContext.complete();
			}
		});


	}
}
