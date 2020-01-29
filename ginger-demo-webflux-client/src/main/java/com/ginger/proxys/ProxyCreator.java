/**
 * 
 */
package com.ginger.proxys;

/**
 * @Description: 创建代理类接口
 * @author 姜锋
 * @date 2019年5月1日 下午3:44:04 
 * @version V1.0   
 *
 */
public interface ProxyCreator {
	
	/**
	 * 
	 * @Description: 创建代理类
	 * @author 姜锋
	 * @date 2019年5月1日 下午3:45:12
	 * @param @param type
	 * @param @return
	 * @return Object
	 * @throws
	 */
	Object createProxy(Class<?> type);
}
