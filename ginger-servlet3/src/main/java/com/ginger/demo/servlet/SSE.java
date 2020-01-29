/**
 * 
 */
package com.ginger.demo.servlet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: SSE (Server-Sent events )
 * @author 姜锋
 * @date 2019年4月11日 下午4:09:10 
 * @version V1.0   
 *
 */
@WebServlet("/SSE")
public class SSE extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public SSE() {
		
	}
	/* （非 Javadoc）
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/event-stream");
		resp.setCharacterEncoding("utf-8");
		for (int i = 0; i < 100; i++) {
			resp.getWriter().write("event:me\n");
			//格式 data + 数据 +俩个空格
			resp.getWriter().write("data:" + i + "\n\n");
			resp.getWriter().flush();;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}
	/* （非 Javadoc）
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
