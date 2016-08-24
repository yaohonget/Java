package com.hong.dev.practice.java8;

import com.hong.dev.practice.java8.capi.functionalInterface.IConverter;
import com.hong.dev.practice.java8.capi.functionalInterface.ISomethingFactory;
import com.hong.dev.practice.java8.internal.Calculator;
import com.hong.dev.practice.java8.internal.lambda.Lambda;

/**
 * {@link http://winterbe.com/posts/2014/03/16/java-8-tutorial/}
 * 
 * @author HYao
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Hello, World!");

		// default method
		Calculator calculator = new Calculator();
		double res = calculator.calculate(20);
		System.out.println(res);

		// lambda
		Lambda lambda = new Lambda();
		lambda.sort(lambda.names);

		// functional interface
		IConverter<String, String> converter = (from) -> "new " + from;
		System.out.println(converter.convert("world"));

		// method reference
		// the class must be located in the same package?
		IConverter<String, String> converter2 = Something2::startWith;
		System.out.println(converter2.convert("Hi"));

		IConverter<String, Integer> converter3 = String::valueOf;
		System.out.println(converter3.convert(1234));

		// constructor reference
		ISomethingFactory<Something> factory = Something::new;
		factory.create("yoo", "hoo").printThings();

		// lambda could read the final or implicitly final local variable which
		// is out of the scope of the expression. For instance or static field
		// there is no limit.
		// The default methods of interface can not be accessed in lambda
		// expression.
		int i1 = 1;
		final int i2 = 2;
		IConverter<String, Integer> converter4 = (i) -> i1 + i + "";
		System.out.println(converter4.convert(23));
		// i1 = 20; // Compile error.
		IConverter<String, Integer> converter5 = (i) -> i2 + i * 2 + "";
		System.out.println(converter5.convert(89));

	}
}

class Something2 {
	static String startWith(String pStr) {
		return String.valueOf(pStr.charAt(0));
	}
}