/**
 * 
 */
package com.ginger.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ginger.handlers.UserHandler;

/**
 * @Description: 入口路由
 * @author 姜锋
 * @date 2019年5月1日 下午2:28:31 
 * @version V1.0   
 *
 */
@Configuration
public class EntranceRouter {
	
	@Bean
	RouterFunction<ServerResponse> userRouter(UserHandler handler) {
		return RouterFunctions.nest(
				// 相当于类上面的 @RequestMapping("/user")
				RequestPredicates.path("/user"),
				// 下面的相当于类里面的 @RequestMapping
				// 得到所有用户
				RouterFunctions.route(RequestPredicates.GET("/"), handler::getUsers)
				// 创建用户
				.andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),
								handler::createUser)
				// 删除用户
				.andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteUserById));
	}
}
