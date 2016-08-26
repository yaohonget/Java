package com.hong.paralle.sync;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerProducer {

	public static void main(String [] args) {
		Bank bank = new Bank();
		Consumer con = new Consumer();
		Producer pro = new Producer();
		
		ExecutorService exService = Executors.newFixedThreadPool(10);//newSingleThreadScheduledExecutor();//Executors.newFixedThreadPool(10);
		Collection<Callable<Integer>> tasks = Arrays.asList(con.save(bank), pro.draw(bank)); 
		
		try {
			exService.invokeAll(tasks);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}

class Consumer {
	int save = 0;
		
	Callable<Integer> save(Bank b) {
		return () -> {save++; b.save();return 1;};
	}
}

class Producer {
	int draw = 0;
	
	Callable<Integer> draw(Bank b) {
		return () -> {draw++; b.draw();return 1;};
	}
}

class Bank {
	
	public int amount = 0;
	
	synchronized void save() {
		amount++;
		System.out.println("save 1, amount : " + amount);
	}
	
	synchronized void draw() {
		amount--;
		System.out.println("draw 1, amount : " + amount);
	}
}
