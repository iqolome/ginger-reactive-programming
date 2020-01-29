/**
 * 
 */
package com.ginger.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ginger.domain.User;

import reactor.core.publisher.Flux;

/**
 * @Description: {@link User} {@link Repository}
 * @author 姜锋
 * @date 2019-02-06 10:31
 * @version V1.0   
 *
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String>{
	
	
	Flux<User> findByAgeBetween(int start, int end);
	
	@Query("{age:{$gte:20,$lte:50}}")
	Flux<User> oldUser();
}