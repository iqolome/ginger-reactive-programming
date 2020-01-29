/**
 * 
 */
package com.ginger.handler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.ginger.beans.MethodInfo;
import com.ginger.beans.ServerInfo;

import reactor.core.publisher.Mono;

/**
 * @Description: WebClient
 * @author 姜锋
 * @date 2019年5月1日 下午5:10:31 
 * @version V1.0   
 *
 */
public class WebClientRestHandler implements RestHandler {
	private WebClient client;
	/* （非 Javadoc）
	 * @see com.ginger.handler.RestHandler#init(com.ginger.beans.ServerInfo)
	 */
	@Override
	public void init(ServerInfo serverInfo) {
		this.client = WebClient.create(serverInfo.getUrl());
	}
	/* （非 Javadoc）
	 * @see com.ginger.handler.RestHandler#invokeRest(com.ginger.beans.ServerInfo, com.ginger.beans.MethodInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object invokeRest(ServerInfo serverInfo, MethodInfo methodInfo) {
		//请求方法
		RequestBodySpec requestBodySpec = this.client.method(methodInfo.getMethod())
		//请求URL和参数
		.uri(methodInfo.getUrl(),methodInfo.getParams())
		.accept(MediaType.APPLICATION_JSON_UTF8);
		
		ResponseSpec retrieve = null;
		//判断时候带了Body
		if(methodInfo.getBody() !=null) {
			//发出请求
		  retrieve = requestBodySpec.body(methodInfo.getBody(),methodInfo.getBodyElementType()).retrieve();
		}else {
			retrieve = requestBodySpec.retrieve();
		}
		retrieve.onStatus(status -> status.value()==404, response ->
			Mono.just(new RuntimeException("Not Found")));
		if(methodInfo.isReturnFlux()) {
			return retrieve.bodyToFlux(methodInfo.getReturnElementType());
		}else {
			return retrieve.bodyToMono(methodInfo.getReturnElementType());
		}
	}
}
