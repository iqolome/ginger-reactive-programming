/**
 * 
 */
package com.ginger.steam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description: 流的创建
 * @author 姜锋
 * @date 2019年4月7日 下午2:46:46 
 * @version V1.0   
 *
 */
public class SteamDemo2 {
	
	public static void main(String[] args) {
		
		List<String> list= new ArrayList<>();
		//从集合创建
		list.stream();
		list.parallelStream();
		
		//数组创建
		Arrays.stream(new int[]{1,2,3,4,5});
		
		//创建数字流
		
		IntStream.of(1,2,3,4,5);
		IntStream.range(1, 10);
		//使用random创建无限流,不进行终止将会报错,无限进行下去
		new Random().ints().limit(10);
		
		//自己产生流
		Random random = new Random();
		Stream.generate(()-> random.nextInt()).limit(20);
	}
}
