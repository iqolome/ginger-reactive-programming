/**
 * 
 */
package com.ginger.lambda;

import java.util.function.Function;

/**
 * @Description: 级联表达式和柯里化✿
 *  柯里化: 把多个参数的函数转化为只有一个参数的函数
 *  柯里化的目标: 函数标准化
 *  高级函数: 返回函数的函数
 * @author 姜锋
 * @date 2019年4月7日 上午11:08:10 
 * @version V1.0   
 *
 */
public class CurryDemo {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		// 级联表达式 实现了x+Y的效果
		Function<Integer, Function<Integer, Integer>> fun1 = x -> y-> x+y;
		System.out.println(fun1.apply(5).apply(3));
		
		Function<Integer, Function<Integer, Function<Integer,Integer>>> fun2 = x -> y -> z -> x+y+z;
		System.out.println(fun2.apply(10).apply(10).apply(10));
		
		int[] nums = {2,3,4};
		Function fun3 = fun2;
		for (int i = 0; i < nums.length; i++) {
			if(fun3 instanceof Function) {
				Object obj = fun3.apply(nums[i]);
				if(obj instanceof Function) {
					fun3 = (Function) obj;
				}else {
					System.out.println("调用结束"+obj);
				}
			}
		}
	}
}
