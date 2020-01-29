/**
 * 
 */
package com.ginger.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @Description: 不合格抛出: 该邮箱地址已绑定
 * @author 姜锋
 * @date 2018年12月17日 下午4:59:19 
 * @version V1.0   
 *
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerificationParameterValidator.class)
public @interface VerificationParameter {
	
	String message() default "参数不合法";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
