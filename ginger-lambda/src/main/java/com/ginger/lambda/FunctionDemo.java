/**
 * 
 */
package com.ginger.lambda;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Description: 函数式编程
 * @author 姜锋
 * @date 2019年4月4日 下午3:49:03 
 * @version V1.0   
 *
 */
public class FunctionDemo {
	
	public static void main(String[] args) {
		
		
		//断言函数接口
		//IntPredicate
		Predicate<Integer> predicate  = i-> i>3;
		
		System.out.println("输出判断:"+predicate.test(-13));
		
		//消费函数接口
		//IntConsumer
		Consumer<String> consumer = s -> System.out.println(s);
		consumer.accept("输入的数据: 我是谁");
		
		
		
		
	}
}
