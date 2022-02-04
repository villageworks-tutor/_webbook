package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * すべての業務サーブレットが継承する基底サーブレット
 * Servlet implementation class BaseServlet
 */
//@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	protected static final String DIR_JSP = "/WEB-INF/views";

	/**
	 * 指定したURLに遷移する。
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param nextPage 遷移先URL
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void gotoPage(HttpServletRequest request, HttpServletResponse response, String nextPage) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
