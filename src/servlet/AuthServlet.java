package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AuthBean;
import dao.AuthDAO;
import dao.DbUtils;
import jp.villageworks.core.DataUtils;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends BaseServlet {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** クラス定数群 */
	private static final String PARAM_KEY_ACTION = "action";
	private static final String PARAM_KEY_CARD = "card";
	private static final String PARAM_KEY_PASSWORD = "passwor";
	private static final String SESSION_KEY_AUTH = "auth";
	private static final String REQUEST_KEY_MESSAGE = "message";
	private static final String ACTION_LOGIN = "login";
	private static final String ACTION_LOGOUT = "logout";
	private static final String URL_LOGIN = DIR_JSP + "/login.jsp";
	private static final String URL_MAIN = DIR_JSP + "/main.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter(PARAM_KEY_ACTION);
		String nextPage = DIR_JSP;
		String message = "";
		if (DataUtils.isNull(nextPage) || DataUtils.isEmpty(action)) {
			nextPage += URL_LOGIN;
		} else if (action.equals(ACTION_LOGIN)) {
			String card = request.getParameter(PARAM_KEY_CARD);
			String password = request.getParameter(PARAM_KEY_PASSWORD);
			AuthBean target = new AuthBean(card, password);
			AuthDAO dao = new AuthDAO(DbUtils.getConnection());
			AuthBean auth = dao.getAuth(target);
			if (DataUtils.isNull(auth)) {
				nextPage = URL_LOGIN;
				message = "ログインできませんでした。";
				request.setAttribute(REQUEST_KEY_MESSAGE, message);
			} else {
				// セッションを取得
				HttpSession session = request.getSession();
				AuthBean login = (AuthBean) session.getAttribute(SESSION_KEY_AUTH);
				if (!DataUtils.isNull(login)) {
					session.removeAttribute(SESSION_KEY_AUTH);
					session.invalidate();
					nextPage = URL_LOGIN;
					message = "ログインできませんでした。";
					request.setAttribute(REQUEST_KEY_MESSAGE, message);
				} else {
					nextPage = URL_MAIN;
					// ログイン情報をセッションに登録
					session.setAttribute(SESSION_KEY_AUTH, auth);
				}
			}
		} else if (action.equals(ACTION_LOGOUT)) {
			HttpSession session = request.getSession(false);
			session.removeAttribute(SESSION_KEY_AUTH);
			session.invalidate();
			nextPage = URL_LOGIN;
			message = "ログアウトしました。";
			request.setAttribute(REQUEST_KEY_MESSAGE, message);
		}
		this.gotoPage(request, response, nextPage);
	}

}
