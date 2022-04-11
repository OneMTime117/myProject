package com.yh.common.demo.reactor;

import reactor.core.publisher.Mono;

public class ReactorDemo {

	public static void main(String[] args) {
		Mono.just("name")
				.map(String::toUpperCase)
				.subscribe(System.out::println);

	}
}
