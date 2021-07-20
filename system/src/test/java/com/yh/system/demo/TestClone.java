package com.yh.system.demo;

import lombok.Data;

@Data
public class TestClone implements Cloneable {

	private String name;
	private String sex;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public static void main(String[] args) throws CloneNotSupportedException {


		TestClone testClone = new TestClone();
		testClone.setName("yh");
		testClone.setSex("1");
		TestClone clone = (TestClone) testClone.clone();
		System.out.println(clone.toString());

	}
}
