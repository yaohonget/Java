package com.hong.dev.practice.java8.capi.defaultMethod;

/**
 * 
 * @author HYao
 *
 */
public interface IFormula {
	double calculate(int a);
	
	// default method
	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}
