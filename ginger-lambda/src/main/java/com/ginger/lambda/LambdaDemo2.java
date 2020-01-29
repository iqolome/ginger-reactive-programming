/**
 * 
 */
package com.ginger.lambda;

/**
 * @Description: admbda学习 接口多继承 以及使用默认方法
 * @author 姜锋
 * @date 2019-01-18 16:34
 * @version V1.0   
 *
 */
/**
 * JDK8 新特性2 默认方法 及 默认实现方法
 */
@FunctionalInterface
interface Interface11 {
	int doubleNum(int i);
	default int add(int x, int y) {
		return x + y;
	}
}

@FunctionalInterface
interface Interface22 {
	int doubleNum(int i);
	default int add(int x, int y) {
		return x + y;
	}
}

@FunctionalInterface
interface Interface33 extends Interface22,Interface11{

	/* （非 Javadoc）
	 * @see com.ginger.lambda.Interface1#add(int, int)
	 */
	@Override
	default int add(int x, int y) {
		// TODO 自动生成的方法存根
		return Interface11.super.add(x, y);
	}
	
}

public class LambdaDemo2 {
}
