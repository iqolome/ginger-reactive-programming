/**
 * 
 */
package com.ginger.handler;

import com.ginger.beans.MethodInfo;
import com.ginger.beans.ServerInfo;

/**
 * @Description: Rest请求处理
 * @author 姜锋
 * @date 2019年5月1日 下午4:23:37 
 * @version V1.0   
 *
 */
public interface RestHandler {

	/**
	 * @Description: 输出化服务器信息
	 * @author 姜锋
	 * @date 2019年5月1日 下午4:28:57
	 * @param @param serverInfo
	 * @return void
	 * @throws
	 */
	void init(ServerInfo serverInfo);

	/**
	 * @Description: 调用Rest请求,返回结果
	 * @author 姜锋
	 * @date 2019年5月1日 下午4:29:19
	 * @param @param serverInfo
	 * @param @param methodInfo
	 * @param @return
	 * @return Object
	 * @throws
	 */
	Object invokeRest(ServerInfo serverInfo, MethodInfo methodInfo);
	
	
}
