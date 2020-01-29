/**
 * 
 */
package com.ginger.demo.reactive;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

/**
 * @Description: 
 * 
 * 	Processor 需要继承SubmissionPublisher并实现Processor接口
 * 	输入源integer,过滤小于0,然后转换成字符串发布出去
 * @author 姜锋
 * @date 2019年4月10日 下午4:34:32 
 * @version V1.0   
 *
 */
class MyProcessor extends SubmissionPublisher<String> implements Processor<Integer, String>{
	
	private Subscription subscription;
	
	/* （非 Javadoc）
	 * @see java.util.concurrent.Flow.Subscriber#onSubscribe(java.util.concurrent.Flow.Subscription)
	 */
	@Override
	public void onSubscribe(Subscription subscription) {
		//保证订阅关系需要它给发布者响应
		this.subscription = subscription;
		//请求一个数据
		this.subscription.request(1);
		
	}

	/* （非 Javadoc）
	 * @see java.util.concurrent.Flow.Subscriber#onNext(java.lang.Object)
	 */
	@Override
	public void onNext(Integer item) {
		
		//接受到一个数据,处理
		System.out.println("处理器接收到的数据: ->" + item);
		
		//过滤小于0的,然后发布出去
		if(item > 0) {
			this.submit("处理器转换后的数据"+item);
		}
		//处理完调用request再请求一个数据
		this.subscription.request(1);
		//或者 已经达到了目标,调用cancal告诉发布者不再接受数据
		//this.subscription.cancel();
		
	}

	/* （非 Javadoc）
	 * @see java.util.concurrent.Flow.Subscriber#onError(java.lang.Throwable)
	 */
	@Override
	public void onError(Throwable throwable) {
		//如果出现了异常例如(处理数据时出现了异常)
		throwable.printStackTrace();
		System.out.println("处理器调用出现异常");
		//告诉发布者 ,后面不接受数据了
		this.subscription.cancel();
				
	}

	/* （非 Javadoc）
	 * @see java.util.concurrent.Flow.Subscriber#onComplete()
	 */
	@Override
	public void onComplete() {
		//所有数据处理完了(发布者关了)
		System.out.println("处理器处理完毕");
		this.close();
		
	}
	
	
}

public class FlowDemo2 {
	
	public static void main(String[] args) throws InterruptedException {
		/**
		 * 1.定义发布者 数据类型<Integer>
		 *   使用JDK9自带的SubmissionPublisher实现了Publisher接口
		 */
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
		
		/**
		 * 2.定义处理器,对数据进行过滤,并转换成String类型
		 */
		MyProcessor myProcessor = new MyProcessor();
		/**
		 * 3.发布者和处理器简历订阅关系
		 */
		publisher.subscribe(myProcessor);
		
		//4.定义最终消费者,消费 String 类型
		Subscriber<String> subscriber = new Subscriber<>(){
			private Subscription subscription;
			@Override
			public void onSubscribe(Subscription subscription) {
				// 保存订阅关系, 需要用它来给发布者响应
				this.subscription = subscription;

				// 请求一个数据
				this.subscription.request(1);
			}

			@Override
			public void onNext(String item) {
				// 接受到一个数据, 处理
				System.out.println("订阅者接受到数据: " + item);

				// 处理完调用request再请求一个数据
				this.subscription.request(1);

				// 或者 已经达到了目标, 调用cancel告诉发布者不再接受数据了
				// this.subscription.cancel();
			}

			@Override
			public void onError(Throwable throwable) {
				// 出现了异常(例如处理数据的时候产生了异常)
				throwable.printStackTrace();

				// 我们可以告诉发布者, 后面不接受数据了
				this.subscription.cancel();
			}

			@Override
			public void onComplete() {
				// 全部数据处理完了(发布者关闭了)
				System.out.println("订阅者处理完了!");
			}
			
		};
		//5.处理器和最终订阅者建立关系
		myProcessor.subscribe(subscriber);
		// 6.生产数据并发布
		//这里忽略生产过程
		publisher.submit(111);
		publisher.submit(-11231);
		publisher.submit(-111);
		publisher.submit(11231);
		publisher.submit(-111);
		publisher.submit(685);
		//7, 结束后 关闭发布者
		//正式环境应该放finally或者try-resource确保关闭
		publisher.close();
		//主线程延迟停止,否则数据还没消费就退出
		Thread.currentThread().join(10000);
		
	}
}
