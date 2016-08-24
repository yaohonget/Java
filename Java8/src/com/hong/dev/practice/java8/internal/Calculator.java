package com.hong.dev.practice.java8.internal;

import com.hong.dev.practice.java8.capi.defaultMethod.IFormula;

public class Calculator implements IFormula {

	@Override
	public double calculate(int a) {		
		return sqrt(a);
	}

}
