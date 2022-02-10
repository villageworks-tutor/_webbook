package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.villageworks.core.DataUtils;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends BaseServlet {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * クラス定数
	 */
	private static final String ACTION_MAIN = "main";
	private static final String ACTION_LOGIN = "login";
	private static final String URL_ERROR = DIR_JSP + "/error/systemerror.jsp";
	private static final String URL_MAIN = DIR_JSP + "/main.jsp";

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータのactionキーを取得
		String action = request.getParameter("action");
		String nextPage = "";
		if (DataUtils.isNull(action) || DataUtils.isEmpty(action) || action.equals(ACTION_MAIN) || action.equals(ACTION_LOGIN)) {
			nextPage = URL_MAIN;
		} else {
			request.setAttribute("message", "不正な操作です。");
			nextPage = URL_ERROR;
		}
		this.gotoPage(request, response, nextPage);
	}

}
