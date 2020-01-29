/**
 * 
 */
package com.ginger.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ginger.domain.User;
import com.ginger.repository.UserRepository;
import com.ginger.util.CheckUtil;

import reactor.core.publisher.Mono;

/**
 * @Description: 用户处理
 * @author 姜锋
 * @date 2019年4月15日 下午5:00:26 
 * @version V1.0   
 *
 */
@Component
public class UserHandler {
	
	private final UserRepository userRepository;
	
	public UserHandler(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	
	/**
	 * 
	 * @Description: 获取所有用户
	 * @author 姜锋
	 * @date 2019年4月15日 下午5:02:16
	 * @param @param request
	 * @param @return
	 * @return Mono<ServerResponse>
	 * @throws
	 */
	public Mono<ServerResponse> getUsers(ServerRequest request){
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(userRepository.findAll(),User.class);
	}
	/**
	 * 
	 * @Description: 创建用户
	 * @author 姜锋
	 * @date 2019年4月15日 下午5:08:04
	 * @param @param request
	 * @param @return
	 * @return Mono<ServerResponse>
	 * @throws
	 */
	public Mono<ServerResponse> createUser(ServerRequest request) {
		// 2.0.0 是可以工作, 但是2.0.1 下面这个模式是会报异常
		Mono<User> user = request.bodyToMono(User.class);
		return user.flatMap(u -> {
			// 校验代码需要放在这里
			CheckUtil.checkName(u.getUserName());
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(this.userRepository.save(u), User.class);
		});
	}
	
	/**
	 * 
	 * @Description: 根据ID删除用户
	 * @author 姜锋
	 * @date 2019年4月15日 下午5:12:29
	 * @param @param request
	 * @param @return
	 * @return Mono<ServerResponse>
	 * @throws
	 */
	public Mono<ServerResponse> deleteUserById(ServerRequest request){
		String id = request.pathVariable("id");
		
		return this.userRepository
				.findById(id)
				.flatMap(user -> this.userRepository.delete(user)
						.then(ServerResponse.ok().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
}
