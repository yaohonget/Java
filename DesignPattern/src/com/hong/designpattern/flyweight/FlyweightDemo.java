package com.hong.designpattern.flyweight;

public class FlyweightDemo {
	/* {src_lang=Java} */
	private static final String colors[] = { "Red", "Green", "Blue", "White",
			"Black" };

	public static void main(String [] args) {
		for (int i = 0; i < 20; ++i) {
			Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
			circle.setX(getRandomX());
			circle.setY(getRandomY());
			circle.setRadius(100);
			circle.draw();
		}
	}

	public static String getRandomColor() {
		return colors[(int) (Math.random() * colors.length)];
	}

	public static Integer getRandomX() {
		return (int) (Math.random() * 100);
	}

	public static Integer getRandomY() {
		return (int) (Math.random() * 100);
	}

}