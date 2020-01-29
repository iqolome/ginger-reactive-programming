/**
 * 
 */
package com.ginger.proxys;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ginger.annotation.ApiServer;
import com.ginger.beans.MethodInfo;
import com.ginger.beans.ServerInfo;
import com.ginger.handler.RestHandler;
import com.ginger.handler.WebClientRestHandler;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description:使用JDK动态代理实现代理类
 * @author 姜锋
 * @date 2019年5月1日 下午4:09:17 
 * @version V1.0   
 *
 */
public class JDKProxyCreator implements ProxyCreator {
	
	private final static Logger logger = LoggerFactory.getLogger(JDKProxyCreator.class);
	/* （非 Javadoc）
	 * @see com.ginger.proxy.ProxyCreator#createProxy(java.lang.Class)
	 */
	@Override
	public Object createProxy(Class<?> type) {
		logger.info("createProxy --> "+type);
		//根据接口得到API服务器信息
		ServerInfo serverInfo = extractServerInfo(type);
		logger.info("serverInfo --> "+serverInfo);
		//给每一个代理类一个实现 防止冲突
		RestHandler restHandler = new WebClientRestHandler();
		// 初始化服务器信息,与Webclient
		restHandler.init(serverInfo);
		return Proxy.
				newProxyInstance(
						this.getClass().getClassLoader(),
						new Class[] {type},
						new InvocationHandler() {
							@Override
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								//根据方法和参数得到调用的信息
								MethodInfo methodInfo = extractMethodInfo(method,args);
								logger.info("methodInfo --> "+methodInfo);
								return restHandler.invokeRest(serverInfo,methodInfo);
							}
							/**
							 * 
							 * @Description: 根据方法定义和调用参数得到调用的相关信息
							 * @author 姜锋
							 * @date 2019年5月1日 下午4:42:13
							 * @param @param method
							 * @param @param args
							 * @param @return
							 * @return MethodInfo
							 * @throws
							 */
							private MethodInfo extractMethodInfo(Method method, Object[] args) {
								MethodInfo methodInfo = new MethodInfo();
								extraUrlAndMethod(method, methodInfo);
								extractRequestParamAndRequestBody(method, args, methodInfo);
								//提取返回对象信息
								extractReturnInfo(method,methodInfo);
								return methodInfo;
							}
							/**
							 * 
							 * @Description:提取返回对象信息
							 * @author 姜锋
							 * @date 2019年5月1日 下午6:08:30
							 * @param @param method
							 * @param @param methodInfo
							 * @return void
							 * @throws
							 */
							private void extractReturnInfo(Method method, MethodInfo methodInfo) {
								//返回Flux还是Mono
								methodInfo.setReturnFlux(method.getReturnType().isAssignableFrom(Flux.class));
								//得到返回对象的实际类型
								methodInfo.setReturnElementType(extractElementType(method.getGenericReturnType()));
							}
							
							/**
							 * 
							 * @Description: 得到泛型类型的实际类型
							 * @author 姜锋
							 * @date 2019年5月1日 下午6:12:31
							 * @param @param genericReturnType
							 * @param @return
							 * @return Class<?>
							 * @throws
							 */
							private Class<?> extractElementType(Type genericReturnType) {
								return (Class<?>)((ParameterizedType)genericReturnType).getActualTypeArguments()[0];
							}
							/**
							 * @Description: 得到请求Param和Body
							 * @author 姜锋
							 * @date 2019年5月1日 下午5:08:12
							 * @param @param args
							 * @param @param methodinfo
							 * @param @param parameters
							 * @param @param params
							 * @return void
							 * @throws
							 */
							private void extractRequestParamAndRequestBody(Method method,
									Object[] args, MethodInfo methodInfo) {
								// 得到调用的参数和body
								Parameter[] parameters = method.getParameters();
								// 参数和值对应的map
								Map<String, Object> params = new LinkedHashMap<>();
								methodInfo.setParams(params);
								for (int i = 0; i < parameters.length; i++) {
									PathVariable pathVariable = parameters[i].getAnnotation(PathVariable.class);
									if(pathVariable !=null) {
										params.put(pathVariable.value(), args[i]);
									}
									
									//是否带有RequestBody
									RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
									if(requestBody !=null) {
										methodInfo.setBody((Mono<?>)args[i]);
										//请求对象的实际类型
										methodInfo.setBodyElementType(
												extractElementType(parameters[i]
														.getParameterizedType()));
									}
								}
							}
							/**
							 * @Description: 得到请求的URL和方法
							 * @author 姜锋
							 * @date 2019年5月1日 下午5:07:02
							 * @param @param methodinfo
							 * @param @param annotations
							 * @return void
							 * @throws
							 */
							private void extraUrlAndMethod(Method method,
									MethodInfo methodInfo) {
								Annotation[] annotations = method.getAnnotations();
								for (Annotation annotation : annotations) {
									if(annotation instanceof GetMapping) {
										GetMapping getMapping = (GetMapping) annotation;
										methodInfo.setUrl(getMapping.value()[0]);
										methodInfo.setMethod(HttpMethod.GET);
									}else if(annotation instanceof PostMapping) {
										PostMapping postMapping = (PostMapping) annotation;
										methodInfo.setUrl(postMapping.value()[0]);
										methodInfo.setMethod(HttpMethod.POST);
									}else if(annotation instanceof DeleteMapping) {
										DeleteMapping deleteMapping = (DeleteMapping) annotation;
										methodInfo.setUrl(deleteMapping.value()[0]);
										methodInfo.setMethod(HttpMethod.DELETE);
									}else if(annotation instanceof PutMapping) {
										PutMapping putMapping = (PutMapping) annotation;
										methodInfo.setUrl(putMapping.value()[0]);
										methodInfo.setMethod(HttpMethod.PUT);
									}
								}
							}
						}
						);
	}


	/**
	 * @Description: 提取服务器信息
	 * @author 姜锋
	 * @date 2019年5月1日 下午4:12:03
	 * @param @param type
	 * @param @return
	 * @return ServerInfo
	 * @throws
	 */
	private ServerInfo extractServerInfo(Class<?> type) {
		ServerInfo serverInfo = new ServerInfo();
		ApiServer apiServer = type.getAnnotation(ApiServer.class);
		serverInfo.setUrl(apiServer.value());
		return serverInfo;
	}
}
