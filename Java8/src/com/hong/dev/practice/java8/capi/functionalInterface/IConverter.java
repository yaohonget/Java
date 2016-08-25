package com.hong.dev.practice.java8.capi.functionalInterface;

@FunctionalInterface
public interface IConverter<T, F> {
	T convert(F from);
	default void printType() {
		System.out.println(this.getClass().getName());
	}
}
