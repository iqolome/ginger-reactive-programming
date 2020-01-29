/**
 * 
 */
package com.ginger.exceptions;

/**
 * @Description: 检查参数异常
 * @author 姜锋
 * @date 2019年4月16日 下午1:52:23 
 * @version V1.0   
 *
 */
public class CheckParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 出错字段的名字
	 */
	private String fieldName;
	
	/**
	 * 出错字段的值
	 */
	private String fieldValue;
	
	public CheckParameterException() {
		super();
	}

	public CheckParameterException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CheckParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public CheckParameterException(String message) {
		super(message);
	}

	public CheckParameterException(Throwable cause) {
		super(cause);
	}

	public CheckParameterException(String fieldName, String fieldValue) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	/**
	 * @return fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName 要设置的 fieldName
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return fieldValue
	 */
	public String getFieldValue() {
		return fieldValue;
	}

	/**
	 * @param fieldValue 要设置的 fieldValue
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
}
