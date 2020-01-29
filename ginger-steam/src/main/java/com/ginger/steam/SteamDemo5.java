/**
 * 
 */
package com.ginger.steam;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description: 并行流
 * @author 姜锋
 * @date 2019年4月8日 下午2:46:42 
 * @version V1.0   
 *
 */
public class SteamDemo5 {
	
	public static void main(String[] args) {
		
		
		/**
		 * 并行处理
		 */
		// 调用parallel 并行处理
		//long count = IntStream.range(1, 100).parallel().peek(SteamDemo5::debug).sum();
		//
		
		
		
		/**
		 * 多次调用串行 并行 以最后一次调用为准
		 */
//		IntStream.range(1, 100)
//		//调用parallel 并行处理流
//		.parallel()
//		.peek(SteamDemo5::debug)
//		//调用 sequential产生并行流
//		.sequential().peek(SteamDemo5::debug2).count();
		
		/**
		 * 并行流使用的线程池 是ForkJoinPool.commonPool
		 * 默认的线程数是CPU核心数
		 * 使用这个属性可修改默认线程数
		 */
//		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
//		IntStream.range(1, 100)
//		.parallel()
//		.peek(SteamDemo5::debug).count();
		
		
		/**使用自己的线程池,不使用默认线程池,防止任务被阻塞
		 * 线程名称是ForkJoinPool-1
		 */
		ForkJoinPool threadPool = new ForkJoinPool(20);
		threadPool.submit(()->IntStream.range(1, 100)
				.parallel()
				.peek(SteamDemo5::debug).count());
		threadPool.shutdown();
		
		
		synchronized (threadPool) {
			try {
				threadPool.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	public static void debug(int i) {
		
		System.out.println(Thread.currentThread().getName()+"debug 并行处理"+i);
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("并行处理睡觉睡出问题了"+i);
			e.printStackTrace();
			
		}
	}
	
public static void debug2(int i) {
		
		System.err.println("debug 串行处理"+i);
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			System.err.println("串行处理睡觉睡出问题了"+i);
			e.printStackTrace();
			
		}
	}
	
	
}
