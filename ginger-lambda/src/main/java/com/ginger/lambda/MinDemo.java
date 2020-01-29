/**
 * 
 */
package com.ginger.lambda;

import java.util.stream.IntStream;

/**
 * @Description: 最小值
 * @author 姜锋
 * @date 2019-01-14 15:59
 * @version V1.0   
 *
 */
public class MinDemo {
	
	//命令式编程
	public static Integer getMinOfImperativeProgramming(int[] nums) {
		int min = Integer.MAX_VALUE;
		for (int i : nums) {
			if(i<min) {
				min = i;
			}
		}
		return min;
		
	}
	
	//函数式编程 在JDK8以后
	public static Integer getMinOfFunctionalProgrammingg(int[] nums){
		/**
		 * parallel() 并行计算
		 */
		return IntStream.of(nums).parallel().min().getAsInt();
	}
	
	public static void main(String[] args) {
		int[] nums = {23,-98,81,56,44,12,-98,656,5684,-886};
		System.out.println("输出最小值: "+getMinOfImperativeProgramming(nums));
		System.out.println("输出最小值: "+getMinOfFunctionalProgrammingg(nums));
		
	}
}
