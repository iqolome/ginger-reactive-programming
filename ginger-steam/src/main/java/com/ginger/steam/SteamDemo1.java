/**
 * 
 */
package com.ginger.steam;

import java.util.stream.IntStream;

/**
 * @Description: Steam流编程
 * @author 姜锋
 * @date 2019年4月7日 下午12:57:09
 * @version V1.0
 */
public class SteamDemo1 {
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3};
		// 外部迭代
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		System.out.println("外部迭代输出结果" + sum);
		// 使用Steam的内部迭代
		int sum2 = IntStream.of(nums).sum();
		System.out.println("内部迭代输出结果" + sum2);
		
		//中间操作/终止操作 惰性求值
		// map为中间操作(就是返回流的操作)
		// sum为终止操作
		// 惰性求值就是终止操作未调用的情况下,中间操作不会执行
		int sum3 = IntStream.of(nums).map(SteamDemo1::doubleNum).sum();
		System.out.println("惰性求值输出结果" + sum3);
		System.out.println("惰性求值就是终止操作未调用的情况下,中间操作不会执行");
		IntStream.of(nums).map(SteamDemo1::doubleNum);
	}
	
	public static int doubleNum(int i) {
		System.out.println("乘2");
		return i*2;
	}
}
