/**
 * 
 */
package com.ginger;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ginger.api.IUserApi;
import com.ginger.proxys.JDKProxyCreator;
import com.ginger.proxys.ProxyCreator;

/**
 * @Description: 客户端启动类
 * @author 姜锋
 * @date 2019年5月1日 下午3:07:34 
 * @version V1.0   
 *
 */
@SpringBootApplication
public class DemoRestClientApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoRestClientApplication.class, args);
	}
	
	/**
	 * 
	 * @Description: 创建JDK代理工具类
	 * @author 姜锋
	 * @date 2019年5月1日 下午4:37:36
	 * @param @return
	 * @return ProxyCreator
	 * @throws
	 */
	@Bean
	ProxyCreator jdkProxyCreator() {
		return new JDKProxyCreator();
	}
	
	@Bean
	FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator) {
		return new FactoryBean<IUserApi>() {
			/**
			 * 返回代理对象
			 */
			@Override
			public IUserApi getObject() throws Exception {
				return (IUserApi)proxyCreator.createProxy(this.getObjectType());
			}

			@Override
			public Class<?> getObjectType() {
				return IUserApi.class;
			}
		};
	}
}
