package com.hong.paralle.sync;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * http://www.journaldev.com/1061/thread-safety-in-java
 * 
 * @author HYao
 *
 */
public class ConsumerProducer {

	public static void main(String[] args) {
		Bank bank = new Bank();
		Consumer con = new Consumer();
		Producer pro = new Producer();

		ExecutorService exService = Executors.newFixedThreadPool(10);// newSingleThreadScheduledExecutor();//Executors.newFixedThreadPool(10);

		Collection<Callable<Integer>> tasks = Arrays.asList(con.save(bank), pro.draw(bank));

		try {
			exService.invokeAll(tasks);
			exService.shutdownNow();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Consumer {
	int save = 0;

	Callable<Integer> save(Bank b) {
		return () -> {
			synchronized (b) {
				save++;
				b.save();
				return 1;
			}
		};
	}
}

class Producer {
	int draw = 0;

	Callable<Integer> draw(Bank b) {
		return () -> {
			synchronized (b) {
				draw++;
				b.draw();
				return 1;
			}
		};
	}
}

class Bank {

	public Object lock;

	public int amount = 0;

	void save() {
		// synchronized (lock) {
		amount++;
		System.out.println("save 1, amount : " + amount);
		// }
	}

	void draw() {
		// synchronized(lock) {
		amount--;
		System.out.println("draw 1, amount : " + amount);
		// }
	}
}
