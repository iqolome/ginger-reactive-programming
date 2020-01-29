/**
 * 
 */
package com.ginger.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ginger.annotation.ApiServer;
import com.ginger.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description: 用户API
 * @author 姜锋
 * @date 2019年5月1日 下午3:08:46 
 * @version V1.0   
 *
 */
@ApiServer("http://localhost:8080/user")
public interface IUserApi {
	
	@GetMapping("/")
	Flux<User> getUsers();
	
	@GetMapping("/{id}")
	Mono<User> getUserById(@PathVariable("id") String userid);
	
	@DeleteMapping("/{id}")
	Mono<Void> deleteUserById(@PathVariable("id") String userid);
	
	@PostMapping("/")
	Mono<User> createUser(@RequestBody Mono<User> user);
}
