/**
 * 
 */
package com.ginger.steam;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 流的终止操作
 * @author 姜锋
 * @date 2019年4月8日 上午11:30:58 
 * @version V1.0   
 *
 */
public class SteamDemo4 {
	
	
	public static void main(String[] args) {
		
		String str = "my name is ginger";
		
		//parallel 使用并行流 forEach不保证顺序
		//str.chars().parallel().forEach(i -> System.out.print((char)i));
		//parallel 使用并行流 forEachOrdered保证顺序
		str.chars().parallel().forEachOrdered(i -> System.out.print((char)i));
		
		//收集到List
		List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
		System.out.println(list);
		
		//使用reduce拼接字符串
		Optional<String> letters = Stream.of(str.split(" ")).reduce((s1,s2) -> s1+"|"+s2 );
		System.out.println(letters.orElse(""));
		
		//带初始值的reduce
		String reduce = Stream.of(str.split(" ")).reduce("",(s1,s2) -> s1+"|"+s2 );
		System.out.println(reduce);
		
		//计算所有单词总长度
		Integer reduce2 = Stream.of(str.split(" ")).map(s -> s.length()).reduce(0,(s1,s2) -> s1+s2 );
		System.out.println(reduce2);
		
		//max的使用
		 Optional<String> max = Stream.of(str.split(" ")).max((s1,s2)-> s1.length()-s2.length());
		System.out.println(max);
		
		//使用findFirst短路操作
		OptionalInt findFirst = new Random().ints().findFirst();
		System.out.println(findFirst);
		
		
	}
}
