package com.yh.system.demo;

@FunctionalInterface
public interface FunctionalDemo {

	String getName();

	boolean equals(Object obj);

	default void  setName(String name){
		System.out.println(name);
	}
}
