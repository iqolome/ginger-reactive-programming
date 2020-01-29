/**
 * 
 */
package com.ginger.util;

import java.util.stream.Stream;

import com.ginger.exceptions.CheckParameterException;

/**
 * @Description: 检查工具类 
 * @author 姜锋
 * @date 2019年5月1日 下午2:19:57 
 * @version V1.0   
 *
 */
public class CheckUtil {
	
	private static final String[] INVALID_NAMES = { "admin", "guanliyuan" };

	/**
	 * 校验名字, 不成功抛出校验异常
	 * 
	 * @param name
	 */
	public static void checkName(String value) {
		Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
				.findAny().ifPresent(name -> {
					throw new CheckParameterException("name", value);
				});
	}

}
