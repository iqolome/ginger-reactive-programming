/**
 * 
 */
package com.ginger.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户(类名随意,字段需保持一致)
 * @date 2019-02-06 10:29
 * @version V1.0   
 *
 */
//Set,Get,ToString,EqualsAndHashCode方法
@Data
//链式构建对象
@Builder
//所有参数构造器
@AllArgsConstructor
//无参构造器
@NoArgsConstructor
public class User {
	
	private String userId;
	
	private String userName;
	private String password;
	
	private Integer age;

	
}
