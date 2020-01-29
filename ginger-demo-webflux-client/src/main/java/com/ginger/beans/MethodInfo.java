/**
 * 
 */
package com.ginger.beans;

import java.util.Map;

import org.springframework.http.HttpMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * @Description: 方法调用信息类
 * @author 姜锋
 * @date 2019年5月1日 下午4:18:25 
 * @version V1.0   
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodInfo {
	/**
	 * 请求URL
	 */
	private String url;
	/**
	 * 请求方法
	 */
	private HttpMethod method;
	/**
	 * 请求参数
	 */
	private Map<String,Object> params;
	/**
	 * 请求体
	 */
	private Mono body;
	/**
	 * Body类型
	 */
	private Class<?> bodyElementType;
	
	/**
	 * true:Flux false: Mono
	 */
	private boolean returnFlux;
	
	/**
	 * 返回对象的类型
	 */
	private Class<?> returnElementType;
}
