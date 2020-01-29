/**
 * 
 */
package com.ginger.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ginger.domain.User;
import com.ginger.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 官方推荐注入方式
	 */
	private final UserRepository userRepository;
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * 
	 * @Description: 查询所有用户 以数组形式一次性返回数据
	 * @author 姜锋
	 * @date 2019年4月14日 下午12:34:23
	 * @param @return
	 * @return Flux<User>
	 * @throws
	 */
	@GetMapping
	public Flux<User> getUsers(){
		return userRepository.findAll();
	}
	/**
	 * 
	 * @Description: 查询所有用户 以SEE形式多次返回数据
	 * @author 姜锋
	 * @date 2019年4月14日 下午12:34:46
	 * @param @return
	 * @return Flux<User>
	 * @throws
	 */
	@GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<User> getUsersOfStream(){
		return userRepository.findAll();
	}
	

	/**
	 * 
	 * @Description: 查询20-50用户 以数组形式一次性返回数据
	 * @author 姜锋
	 * @date 2019年4月14日 下午12:34:23
	 * @param @return
	 * @return Flux<User>
	 * @throws
	 */
	@GetMapping("/old")
	public Flux<User> getOldUsers(){
		return userRepository.oldUser();
	}
	/**
	 * 
	 * @Description: 查询所有用户 以SEE形式多次返回数据
	 * @author 姜锋
	 * @date 2019年4月14日 下午12:34:46
	 * @param @return
	 * @return Flux<User>
	 * @throws
	 */
	@GetMapping(value = "/stream/old",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<User> getOldUsersOfStream(){
		return userRepository.oldUser();
	}
	
	/**
	 * @Description: 根据年龄查询用户
	 * @author 姜锋
	 * @date 2019年4月14日 下午2:35:26
	 * @param @param start
	 * @param @param end
	 * @param @return
	 * @return Flux<User>
	 * @throws
	 */
	@GetMapping("/age/{start}/{end}")
	public Flux<User> findByAge(@PathVariable("start")int start,
			@PathVariable("end")int end
			){
		return this.userRepository.findByAgeBetween(start, end);
	}
	/**
	 * @Description: 根据年龄查询用户通过流
	 * @author 姜锋
	 * @date 2019年4月14日 下午2:35:26
	 * @param @param start
	 * @param @param end
	 * @param @return
	 * @return Flux<User>
	 * @throws
	 */
	@GetMapping(value="/stream/age/{start}/{end}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<User> findByAgeOfSteam(@PathVariable("start")int start,
			@PathVariable("end")int end
			){
		return this.userRepository.findByAgeBetween(start, end);
	}

	/**
	 * 
	 * @Description: 根据ID查询用户信息
	 * 存在返回用户信息,不存在返回404
	 * @author 姜锋
	 * @date 2019年4月14日 下午1:54:19
	 * @param @param userId
	 * @param @return
	 * @return Mono<ResponseEntity<User>>
	 * @throws
	 */
	@GetMapping("/{userId}")
	public Mono<ResponseEntity<User>> getUser(@PathVariable("userId") String userId){
		return this.userRepository
				.findById(userId).map(u -> new ResponseEntity<User>(u,HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	/**
	 * 
	 * @Description: 新增用户
	 * @author 姜锋
	 * @date 2019年4月14日 下午12:36:57
	 * @param @param user
	 * @param @return
	 * @return Mono<User>
	 * @throws
	 */
	@PostMapping
	public Mono<User> createUser(@Valid @RequestBody User user){
		/**
		 * Spring data jpa中 ,新增和修改都是save,有id为新增,无id为修改
		 * 根据实际情况是否置空ID
		 */
		return userRepository.save(user);
	}
	
	/**
	 * 
	 * @Description: 修改用户
	 * 存在返回状态码200并返回新得数据,不存在返回404
	 * @author 姜锋
	 * @date 2019年4月14日 下午1:31:14
	 * @param @param userId
	 * @param @param user
	 * @param @return
	 * @return Mono<ResponseEntity<User>>
	 * @throws
	 */
	@PutMapping("/{userId}")
	public Mono<ResponseEntity<User>> updateUser(@PathVariable("userId") String userId
			,@Valid @RequestBody User user
			){
		user.setUserId(userId);
		return this.userRepository.findById(userId)
				//flatMap操作数据
				.flatMap(u ->{
					u.setAge(user.getAge());
					u.setPassword(user.getPassword());
					u.setUserName(user.getUserName());
					return this.userRepository.save(u);
				}).
				//map转换数据
				map(u -> new ResponseEntity<User>(u,HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	
	}
	
	/**
	 * 
	 * @Description: 根据ID删除用户
	 * 	存在返回200,不存在返回404
	 * @author 姜锋
	 * @date 2019年4月14日 下午1:02:31
	 * @param @param userId
	 * @param @return
	 * @return Mono<ResponseEntity<Void>>
	 * @throws
	 */
	@DeleteMapping("/{userId}")
	public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("userId") String userId){
		
		//deleteById 没有返回值,不能判断数据是否存在
		//userRepository.deleteById(id)
		return this.userRepository.findById(userId)
		//当你要操作数据,并返回一个Mono 这个时候使用flatMap
		//如果不操作数据,只是对数据进行转换使用map
		.flatMap(user -> this.userRepository.delete(user)
		//如果找到了
		.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
		//如果没找到
		.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}
	
	
	
}
