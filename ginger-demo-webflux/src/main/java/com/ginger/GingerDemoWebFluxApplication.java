/**
 * 
 */
package com.ginger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @Description: SpringBoot启动类
 * @author 姜锋
 * @date 2018年12月26日 下午2:43:34 
 * @version V1.0   
 *
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
public class GingerDemoWebFluxApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(GingerDemoWebFluxApplication.class, args);
	}

}
