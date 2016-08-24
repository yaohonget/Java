package com.hong.dev.practice.java8;

public class Something {
	
	private String thing1;
	private String thing2;
	
	Something(String theThing, String thatThing) {
		this.thing1 = theThing;
		this.thing2 = thatThing;
	}
	
	public void printThings(){
		System.out.println(getThing1() + " - " + getThing2());
	}
	
	static String startWith(String pStr) {
		return String.valueOf(pStr.charAt(0));
	}

	public String getThing1() {
		return thing1;
	}

	public void setThing1(String thing1) {
		this.thing1 = thing1;
	}

	public String getThing2() {
		return thing2;
	}

	public void setThing2(String thing2) {
		this.thing2 = thing2;
	}
}
