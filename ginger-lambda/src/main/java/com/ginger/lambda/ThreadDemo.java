/**
 * 
 */
package com.ginger.lambda;

/**
 * @Description: 线程
 * @author 姜锋
 * @date 2019-01-14 16:12
 * @version V1.0
 */
public class ThreadDemo {
	// JDK8.0以前
	public static void newThreadOld() {
		Runnable targetOld = new Runnable() {
			@Override
			public void run() {
				System.out.println("ok");
			}
		};
		new Thread(targetOld).start();
		;
	}
	// JDK8.0以后 jdk8 lambda
	public static void newThreadNew() {
		Runnable targetNew = ()->System.out.println("lambda ok");
		new Thread(targetNew).start();
		
	}
	public static void main(String[] args) {
		newThreadOld();
		newThreadNew();
		
	}
	

}
