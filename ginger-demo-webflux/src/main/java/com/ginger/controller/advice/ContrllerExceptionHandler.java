/**
 * 
 */
package com.ginger.controller.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.ginger.common.ErrorMessage;

/**
 * @Description: 异常处理切面
 * @author 姜锋
 * @date 2019年4月14日 下午3:34:23
 * @version V1.0
 */
@ControllerAdvice
public class ContrllerExceptionHandler {

	@ExceptionHandler(WebExchangeBindException.class)
	 public ResponseEntity<List<ErrorMessage>> handleBindException(WebExchangeBindException e) {
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		List<ErrorMessage> errorlist = new ArrayList<>();
		allErrors.forEach(objectError ->{
			FieldError fieldError = (FieldError)objectError;
			errorlist.add(new ErrorMessage(fieldError.getObjectName(),fieldError.getField(),fieldError.getDefaultMessage()));
		});
		return new ResponseEntity<>(errorlist, HttpStatus.BAD_REQUEST);
	 }

	
}
