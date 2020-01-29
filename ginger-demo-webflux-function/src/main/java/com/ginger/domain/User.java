/**
 * 
 */
package com.ginger.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ginger.utils.RegularUtils;
import com.ginger.validator.VerificationParameter;

/**
 * @Description: 用户
 * @author 姜锋
 * @date 2019-02-06 10:29
 * @version V1.0   
 *
 */
@Document(collection = "user")
public class User {
	
	@Id
	private String userId;
	
	@NotBlank(message = "用户名不能为空")
	@VerificationParameter
	private String userName;
	@NotBlank(message = "密码不能为空")
	@Pattern(regexp = RegularUtils.REGULAR_PASSWORD_STRONG_TYPE1,message = "密码要求: 8-20个字符，至少1个大写字母，1个小写字母和1个数字，1个特殊字符：")
	private String password;
	
	@Range(min = 10,max = 50)
	private Integer age;

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId 要设置的 userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName 要设置的 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age 要设置的 age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", age=" + age + "]";
	}

	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param age
	 */
	public User(String userId, String userName, String password, Integer age) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.age = age;
	}

	/**
	 * @param userId
	 * @param userName
	 */
	public User(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	/**
	 * 
	 */
	public User() {
	}
	
	
}
