package com.hong.ocp.assertAndExceptions;

public class ClassAssert {
	public static void main(String[] args) {
		boolean flag = false;
		System.out.println("Start");
		assert(flag): "assert information";
		System.out.println("Finish");
	}
}
