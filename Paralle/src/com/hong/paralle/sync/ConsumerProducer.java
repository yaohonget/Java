package com.hong.paralle.sync;

public class ConsumerProducer {
	public static void main(String [] args) {
		Bank bank = new Bank();
		Consumer con = new Consumer();
		Producer pro = new Producer();
		
	}
	
	
	
}

class Consumer {
	int save = 0;
}

class Producer {
	int draw = 0;
}

class Bank {
	
	int amount = 0;
	
	void save() {
		System.out.println("");
	}
	
	void draw() {
		System.out.println("");
	}
}
