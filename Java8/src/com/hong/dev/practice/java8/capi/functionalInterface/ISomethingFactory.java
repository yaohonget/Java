package com.hong.dev.practice.java8.capi.functionalInterface;

import com.hong.dev.practice.java8.internal.lambda.Something;

@FunctionalInterface
public interface ISomethingFactory<P extends Something> {
	P create(String str1, String str2);
}
