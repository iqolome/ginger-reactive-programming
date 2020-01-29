/**
 * 
 */
package com.ginger.controller;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ginger.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description:用户管理
 * @author 姜锋
 * @date 2019年4月10日 下午10:11:07
 * @version V1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);

	@GetMapping("/ginger")
	public User getGinger() {
		logger.info("get Ginger start");
		User user = createUser(String.valueOf(1), "Ginger");
		logger.info("get Ginger end");
		return user;
	}
	@GetMapping("/tom")
	public Mono<User> getTom() {
		logger.info("get Tom start");
		int id = 2;
		String name = "Tom";
		Mono<User> user = Mono.fromSupplier(() -> createUser(String.valueOf(id), name));
		logger.info("get Tom end");
		return user;
	}
	/**
	 * @Description: Flux 
	 * @author 姜锋
	 *  @date 2019年4月11日 下午3:55:28 
	 *  @param @return 
	 *  @throws
	 *  @return Flux<String> 流的方式返回 MediaType.TEXT_EVENT_STREAM_VALUE = text/event-stream
	 */
	@GetMapping(value = "/nums",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> getNumbers() {
		Flux<String> result = Flux.fromStream(IntStream.range(1, 1000).mapToObj(i -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "\n flux data--" + 1;
		}));
		return result;
	}
	public User createUser(String id, String name) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new User(id, name);
	}
}
