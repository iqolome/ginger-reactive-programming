/**
 * 
 */
package com.ginger.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
/**
 * @Description: 
 * @author 姜锋
 * @date 2019年5月1日 下午3:20:59 
 * @version V1.0   
 *
 */
public @interface ApiServer {
	
	String value() default "";
}
