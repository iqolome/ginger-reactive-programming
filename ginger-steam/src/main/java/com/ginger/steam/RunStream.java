/**
 * 
 */
package com.ginger.steam;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @Description: 验证stream运行机制
 * 1.所有操作是链式调用,一个元素只迭代一次
 * 2.每一个中间操作返回一个新的流,
 * 	流里面有一个属性sourceStage执行同一个地方,就是Head
 * 3. Head -> nextStage ->... ->null
 * 4.有状态操作会把无状态操作截断,单独处理
 * 5.并行环境下有状态的中间操作不一定能并行操作
 * 6.parallel/sequential这俩个操作也是中间操作也是返回stream
 * 但是和其他的有区别 ,他们不创建流,他们值修改Head的并行标志
 * @author 姜锋
 * @date 2019年4月9日 下午3:34:11 
 * @version V1.0   
 *
 */
public class RunStream {
	
	public static void print(String s) {
		System.out.println(Thread.currentThread().getName()+" --> "+s);
	}
	public static void main(String[] args) {
		
		Random random = new Random();
		Stream<Integer> steam = Stream
		//产生500 无限流需要断路操作
		.generate(()->random.nextInt()).limit(500)
		//第1个无状态操作
		.peek(s->print("peek"+s))
		//第2个无状态操作
		.filter(s ->{
			print("filter:"+s);
			return s>1000000;
			})
		//有状态操作
		.sorted((i1,i2)->{
			print("排序"+i1+","+i2);
			return i1.compareTo(i2);
			
		})
		//第3个无状态操作
		.filter(s ->{
			print("filter:"+s);
			return s>1000000;
			})
		.peek(s->print("peek2"+s))
		//并行操作
		.parallel()
		;
		steam.count();
		}

	
	
}
