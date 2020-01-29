/**
 * 
 */
package com.ginger.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;

/**
 * @Description: 方法引用 
 * @author 姜锋
 * @date 2019年4月4日 下午4:08:26 
 * @version V1.0   
 *
 */


class Dog{
	private String name = "哈士奇";
	private int food = 100;
	/**
	 * 
	 * @Description: 狗叫
	 * @author 姜锋
	 * @date 2019年4月4日 下午5:57:05
	 * @param @param dog
	 * @return void
	 * @throws
	 */
	public static void bark(Dog dog) {
		System.out.println(dog+"叫唤");
	}
	
	/**
	 * 
	 * @Description: 吃狗粮
	 * 	JDK默认会把当前实例传入到非静态方法,参数名为this,位置是第一个参数
	 * @author 姜锋
	 * @date 2019年4月4日 下午5:56:48
	 * @param @param dog
	 * @return void
	 * @throws
	 */
	public int eat(int num) {
		System.out.println("吃了"+num+"斤");
		return this.food-=num;
	}
	
	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dog [name=" + name + "]";
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public Dog() {
	}

	/**
	 * @param name
	 * @param food
	 */
	public Dog(String name, int food) {
		this.name = name;
		this.food = food;
	}

	/**
	 * @param name
	 */
	public Dog(String name) {
		super();
		this.name = name;
	}
	

	
	
	
}

public class MethodRefrenceDemo {
	
	
	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.eat(20);
		
		//方法引用
		Consumer<String> consumer1 = s -> System.out.println(s);
		consumer1.accept("接收的字符串11111");
		/**
		 * lambda实际上是一个匿名函数
		 * 函数的参数 -> 函数的执行体
		 * 当函数执行体只有一个函数调用且函数参数与 ->左边的参数个数一样的话
		 * 可以缩写 表达式
		 * ↓
		 */
		Consumer<String> consumer = System.out::println;
		consumer.accept("接收的字符串22222");
		//静态方法引用
		Consumer<Dog> consumerDog = Dog::bark;
		consumerDog.accept(dog);
		//非静态方法 使用对象实例的方法
		
		Function<Integer, Integer> function = dog::eat;
		//当函数输入输出一致时可改为一元函数
		IntUnaryOperator unaryFunction = dog::eat;
		System.out.println("还剩下"+function.apply(20));
		System.out.println("还剩下"+unaryFunction.applyAsInt(10));
		//Dog::eat;
		
		//使用类名来引用方法
		BiFunction<Dog,Integer,Integer> eatFunction = Dog::eat;
		System.out.println("还剩下"+eatFunction.apply(dog, 20));
		
		//构造函数的方法引用
		Supplier<Dog> supplier = Dog::new;
		System.out.println("创建了对象"+supplier.get());
		
		//带参数的构造器的方法引用
		Function<String,Dog> argsFunction = Dog::new;
		System.out.println(argsFunction.apply("德国黑背"));
	}
	
	
}
