/**
 * 
 */
package com.ginger.lambda;


@FunctionalInterface
interface IMath{
	
	int add(int x , int y);
}

@FunctionalInterface
interface IMath2{
	
	int sub(int x , int y);
}


/**
 * @Description: 类型推断
 * @author 姜锋
 * @date 2019年4月6日 下午6:25:03 
 * @version V1.0   
 *
 */
public class TypeDemo {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
	//变量类型定义
	IMath iambda = (x,y)->x+y;
	
	//数组里
	IMath[] iambdas = {(x,y)->x+y};
	
	
	//强转
	Object lambda2 = (IMath)(x,y)-> x+y;
	
	
	//返回类型
	IMath createLambda = createLambda();
	TypeDemo demo = new TypeDemo();
	//当方法存在二义性时,使用强转对应的接口解决
	demo.test((IMath2)(x,y) ->x+y);
	}
	public static IMath createLambda() {
	
		return (x,y) ->x+y;
	}
	
	public void test(IMath iambda) {
		
		
	}
	
public void test(IMath2 iambda) {
		
		
	}
}
