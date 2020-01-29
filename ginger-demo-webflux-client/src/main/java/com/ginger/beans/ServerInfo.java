/**
 * 
 */
package com.ginger.beans;

/**
 * @Description: 服务器信息
 * @author 姜锋
 * @date 2019年5月1日 下午4:12:18 
 * @version V1.0   
 *
 */
public class ServerInfo {
	
	private String url;

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url 要设置的 url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param url
	 */
	public ServerInfo(String url) {
		this.url = url;
	}

	/**
	 * 
	 */
	public ServerInfo() {
	}

	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServerInfo [url=" + url + "]";
	}
	
}
