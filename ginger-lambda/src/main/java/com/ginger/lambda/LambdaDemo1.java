/**
 * 
 */
package com.ginger.lambda;

/**
 * @Description: ladmbda学习 函数接口
 * @author 姜锋
 * @date 2019-01-18 16:00
 * @version V1.0
 */


/****
 * lambda 对接口有要求
 * JDK8 新特性1  新引入概念  函数接口
 * 1.接口有且只有一个要实现方法
 * 新增注解@FunctionalInterface 
 * 作为编译期间校验 标识这个接口是函数式接口,
 * 禁止再次添加需要实现其他方法
 * 如果再次添加将会报错
 * 进入JDK8以后 尽量保证接口的单一责任制使接口变小
 * 尽量是接口拆分细致最后保证每一个接口只敢一件事,
 * 接口可以多层继承 要灵活应用出来
 * 从而保证思路清晰 灵活 进而使用lambda表达式方便一些
 * 
 * 
 * 
 * 
 *
 */


@FunctionalInterface
interface Interface1 {
	int doubleNum(int i);
	default int add(int x,int y) {
		return x+y;
	}
}

public class LambdaDemo1 {
	public static void main(String[] args) {
		
		//Interface1 i1 = (i) -> i * 2;
		// 这种是常见写法(推荐 最简单 最简洁)
		Interface1 i2 = i -> i * 2;
		int x = 20;
		int y = 30;
		System.out.println(i2.doubleNum(x));
		System.out.println(i2.add(x,y));
		
		// 可以编写具体类型 一行
		//Interface1 i3 = (int i) -> i * 2;
		
		// 可以编写具体类型 多行
		/*
		Interface1 i4 = (int i) -> {
			
			System.out.println("-----你的代码-------");
		return	i * 2;
		};*/
		
		
	}
}
