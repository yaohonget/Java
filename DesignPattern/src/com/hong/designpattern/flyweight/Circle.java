package com.hong.designpattern.flyweight;

public class Circle implements Shape {
	/* {src_lang=Java} */

	private Integer x;

	private Integer y;

	private Integer radius;

	private String color;

	public Circle(String color) {
		this.color = color;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public void draw() {
		System.out.println("Circle : Draw() [ Color : " + color + ", x : "
				+ x.intValue() + ", y : " + y.intValue() + ", radius : "
				+ radius.intValue() + "]");
	}

}