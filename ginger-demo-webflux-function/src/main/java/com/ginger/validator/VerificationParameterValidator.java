/**
 * 
 */
package com.ginger.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: 参数不合格
 * 
 * @author 姜锋
 * @date 2018年12月10日 下午8:28:33 
 * @version V1.0   
 *
 */
public class VerificationParameterValidator implements ConstraintValidator<VerificationParameter, Object> {
	
	private static final Set<String> INVALID_NAMES = new HashSet<>() {
		private static final long serialVersionUID = 1L;
	{
		add("stream");
		add("root");
		add("admin");
		add("administrator");
		add("user");
	}};
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return !INVALID_NAMES.contains(((String)value).toLowerCase());
	
	}
}
