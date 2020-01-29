/**
 * 
 */
package com.ginger.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @Description: 函数接口1
 * @author 姜锋
 * @date 2019-01-18 16:49
 * @version V1.0   
 *
 */
class MyMoney{
	
	private final int money;
	
	public MyMoney(int money) {
		this.money = money;
		
	}
	
	public void printMoney(Function<Integer, String> moneyFormat) {
		
		System.out.println("我的存款"+moneyFormat.apply(this.money));
	}
}

public class MoneyDemo {
	public static void main(String[] args) {
	
		MyMoney myMoney = new MyMoney(434631646);
		
		Function<Integer, String> moneyFormat = i ->  new DecimalFormat("#,###").format(i);
		//函数式接口链式操作
		myMoney.printMoney(moneyFormat.andThen(s->"人名币"+s));
	
	}
}
