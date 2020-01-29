/**
 * 
 */
package com.ginger.demo.reactive;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

/**
 * @Description: 流
 * @author 姜锋
 * @date 2019年4月10日 下午3:52:38 
 * @version V1.0   
 *
 */
public class FlowDemo {
	
	public static void main(String[] args) throws InterruptedException {
		/**
		 * 1.定义发布者 数据类型<Integer>
		 *   使用JDK9自带的SubmissionPublisher实现了Publisher接口
		 */
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
		
		/**
		 * 2.定义订阅者
		 */
		Subscriber<Integer> subscriber = new Subscriber<Integer>() {
			
			private Subscription subscription;
			
			@Override
			public void onSubscribe(Subscription subscription) {
				//保证订阅关系需要它给发布者响应
				this.subscription = subscription;
				//请求一个数据
				this.subscription.request(1);
			}

			@Override
			public void onNext(Integer item) {
				
				//接受到一个数据,处理
				System.out.println("接收到的数据: ->" + item);
				
				//处理完调用request再请求一个数据
				this.subscription.request(1);
				//或者 已经达到了目标,调用cancal告诉发布者不再接受数据
				//this.subscription.cancel();
			}

			@Override
			public void onError(Throwable throwable) {
				//如果出现了异常例如(处理数据时出现了异常)
				throwable.printStackTrace();
				System.out.println("调用出现异常");
				//告诉发布者 ,后面不接受数据了
				this.subscription.cancel();
				
			}

			@Override
			public void onComplete() {
				//所有数据处理完了(发布者关了)
				System.out.println("处理完毕");
			}
			
		};
		/**
		 * 3. 发布者与订阅者简历订阅关系
		 */
		publisher.subscribe(subscriber);
		/**
		 * 4.生产数据并发布
		 */
		int data = 1;
		//submit是一个阻塞方法,缓冲池满则会阻塞代码执行
		publisher.submit(data);
		publisher.submit(2);
		publisher.submit(3);
		publisher.submit(4);
		publisher.submit(5);
		/**
		 * 5. 结束后 关闭发布者
		 *	正式环境应该放finally或者try-resource确保关闭
		 */
		publisher.close();
		//主线程延迟停止,否则数据还没消费就退出
		Thread.currentThread().join(1000);
	}
}
