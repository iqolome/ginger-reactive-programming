/**
 * 
 */
package com.ginger.steam;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @Description: 流的中间操作
 * @author 姜锋
 * @date 2019年4月7日 下午3:13:48 
 * @version V1.0   
 *
 */
public class StreamDemo3 {
	
	public static void main(String[] args) {
		
		//把每个单词的长度打印出来
		String msg = "my name is ginger";
		Stream.of(msg.split(" ")).filter(s -> s.length()>2).map(s -> s.length())
		.forEach(System.out::println);
		
		//flatMap A-> B 属性 (是个集合) ,最终得到所有的A属性里面的所有B属性集合
		//IntSteam/LongStream并不是Steam的子类=,所以要进行装箱
		Stream.of(msg.split("")).flatMap(s -> s.chars().boxed())
		.forEach(
				i -> System.out.println((char)i.intValue())
				);
		//peek 用于debug 是个中间操作和 forEach是终止操作
		Stream.of(msg.split("")).peek(System.out::println).forEach(System.out::println);
		
		//limit 主要用于无限流
		new Random().ints()
		.filter(i -> i>100 && i<10000)
		.limit(20)
		.forEach(System.out::println);
		
	}
}
