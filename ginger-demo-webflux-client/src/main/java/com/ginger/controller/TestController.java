/**
 * 
 */
package com.ginger.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ginger.api.IUserApi;
import com.ginger.domain.User;

import reactor.core.publisher.Mono;

/**
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 姜锋
 * @date 2019年5月1日 下午3:29:52 
 * @version V1.0   
 *
 */
@RestController
public class TestController {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TestController.class);
	
	//直接注入定义的接口
	@Autowired
	IUserApi userApi;
	
	@GetMapping("/")
	public void test() {
		
		//信息提取
		//不订阅,不会实际发出请求,但会进入我们的代理类
//		userApi.getUsers();
//		userApi.getUserById("123");
//		userApi.deleteUserById("123");
//		userApi.createUser(Mono.just(new User("123","123","123",23)));
		//实现直接调用Rest接口的效果2
//		Flux<User> users = userApi.getUsers();
//		users.subscribe(System.out::println);
		String id = "ginger";
		userApi.getUserById(id).subscribe(u ->{
			logger.info("用户名:"+u.getUserName());
		},e->{
			logger.info("找不到用户:"+e.getMessage());
		});
//		userApi.deleteUserById(id).subscribe();
		//创建用户
		userApi.createUser(Mono.just(User.builder().age(23).userName("Ginger").password("Ginger8800").build()))
		.subscribe(str->{
			logger.info("输出结果:"+str);
		});
		
	}
	
}
