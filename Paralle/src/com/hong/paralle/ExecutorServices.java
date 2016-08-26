package com.hong.paralle;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author HYao
 *
 */
public class ExecutorServices {

	static int taskCount = 0;

	public static void main(String[] args) {

		//
		// ExecutorService executor = Executors.newSingleThreadExecutor();
		//
		ExecutorService executor = Executors.newFixedThreadPool(3);
		//
		// ExecutorService executor = Executors.newCachedThreadPool();
		//
		// ExecutorService executor = Executors.newScheduledThreadPool(3);

		//
		ExecutorService executor2 = Executors.newWorkStealingPool();
		ScheduledExecutorService executor3 = Executors.newScheduledThreadPool(1);

		// submit doesn't wait for the termination of tasks
		// Runable
		executor.submit(() -> {
			String tThreadName = Thread.currentThread().getName();
			System.out.println("Current Thread Name : " + tThreadName);
		});

		// Callable will return a result
		Callable<Integer> callableTask = () -> {
			try {
				taskCount++;
				System.out.println("task " + taskCount + " start to sleep");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("task " + taskCount + "  end to sleep, slept 1 second.");
				return taskCount;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};
		// no result of callable could be received directly from submit
		executor.submit(callableTask);

		// Future will get the result from callable
		Future<Integer> futureTask1 = executor.submit(callableTask);
		System.out.println("future done? " + futureTask1.isDone());
		try {
			Integer result;
			// get will block the current thread to get the result of callable
			// result = futureTask1.get();
			// use Timeout to avoid this issue
			// if the timeout is shorter than the running time of task, a
			// timeout exception would be thrown out.
			result = futureTask1.get(3, TimeUnit.SECONDS);
			System.out.println("future done? " + futureTask1.isDone());
			System.out.println("result: " + result);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");
		try {
			executor2.invokeAll(callables).stream().map(future -> {
				try {
					return future.get(1, TimeUnit.SECONDS);
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}).forEach(System.out::println);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Callable<Integer>> callables2 = Arrays.asList(callableTask, callableTask, callableTask);
		try {
			executor2.invokeAll(callables2).stream().map(future -> {
				try {
					return future.get(1, TimeUnit.SECONDS);
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}).forEach(System.out::println);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Callable<String>> callables3 = Arrays.asList(callableMeth("task1", 2), callableMeth("task2", 1),
				callableMeth("task3", 3));

		try {
			String res = executor2.invokeAny(callables3);
			System.out.println(res);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
		ScheduledFuture<?> future = executor3.schedule(task, 6, TimeUnit.SECONDS);

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
		System.out.printf("Remaining Delay: %sms\n", remainingDelay);
		int initialDelay = 0;
		int period = 1;
		executor3.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		executor3.scheduleWithFixedDelay(task, initialDelay, period, TimeUnit.SECONDS);
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// shut down thread
		try {
			System.out.println("attempt to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(15, TimeUnit.SECONDS);
			
			executor2.shutdown();
			executor2.awaitTermination(15, TimeUnit.SECONDS);
			
			executor3.shutdown();
			executor3.awaitTermination(15, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("tasks interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor2.shutdownNow();
			if (!executor2.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor3.shutdownNow();
			if (!executor3.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}

	}

	//
	static public Callable<String> callableMeth(String result, long sleepSeconds) {
		return () -> {
			TimeUnit.SECONDS.sleep(sleepSeconds);
			return result;
		};
	};
}
