/**
 * 
 */
package com.ginger.handlers;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import com.ginger.exceptions.CheckParameterException;

import reactor.core.publisher.Mono;

/**
 * @Description: 入口路由器
 * @author 姜锋
 * @date 2019年4月15日 下午5:21:12 
 * @version V1.0   
 *
 */
@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler{
	
	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		ServerHttpResponse response = exchange.getResponse();
		// 设置响应头400
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		// 设置返回类型
		response.getHeaders().setContentType(MediaType.TEXT_PLAIN);

		// 异常信息
		String errorMsg = toStr(ex);

		DataBuffer db = response.bufferFactory().wrap(errorMsg.getBytes());

		return response.writeWith(Mono.just(db));
	}

	private String toStr(Throwable ex) {
		// 已知异常
		if (ex instanceof CheckParameterException) {
			CheckParameterException e = (CheckParameterException) ex;
			return e.getFieldName() + ": invalid value " + e.getFieldValue();
		}
		// 未知异常, 需要打印堆栈, 方便定位
		else {
			ex.printStackTrace();
			return ex.toString();
		}

	}
	
}
