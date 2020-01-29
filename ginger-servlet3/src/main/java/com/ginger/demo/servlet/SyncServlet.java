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
 * @Description: 同步Servlet
 * @author 姜锋
 * @date 2019年4月11日 上午10:10:18 
 * @version V1.0   
 *
 */
@WebServlet("/SyncServlet")
public class SyncServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SyncServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long t1 = System.currentTimeMillis();

		// 执行业务代码
		doSomeThing(request, response);

		System.out.println("sync use:" + (System.currentTimeMillis() - t1));
	}

	private void doSomeThing(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 模拟耗时操作
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
		}

		//
		response.getWriter().append("done");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
