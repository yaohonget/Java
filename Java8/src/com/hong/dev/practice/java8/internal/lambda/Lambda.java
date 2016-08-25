package com.hong.dev.practice.java8.internal.lambda;

import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lambda {
	
	public List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
	
	public void sort(List<String> pList) {
		System.out.println(pList);
		Collections.sort(pList, (a, b) -> a.compareTo(b));
		System.out.println(pList);		
	}
	
}
