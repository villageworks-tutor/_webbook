package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberBean;
import dao.DAOException;
import dao.DbUtils;
import dao.MemberDAO;
import jp.villageworks.core.DataUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String nextPage = DIR_JSP;
		if (DataUtils.isNull(action) || DataUtils.isEmpty(action) || action.equals("search")) {
			nextPage = DIR_JSP + "/user/searchEntry.jsp";
		} else if (action.equals("searchExecute")) {
			// リクエストパラメータを取得
			String key = request.getParameter("key");
			// 遷移先URLを設定
			nextPage = DIR_JSP + "/user/searchEntry.jsp";
			try {
				MemberDAO dao = new MemberDAO(DbUtils.getConnection());
				List<MemberBean> list = dao.getByEmail(key);
				// リクエストスコープに結果を設定
				request.setAttribute("members", list);
				String message = "";
				if (list.size() == 0) {
					// 検索結果の件数が0件の場合はメッセージを設定
					message = "該当する利用者は見つかりませんでした。";
				} else {
					message = list.size() + "件の利用者が見つかりました。";
				}
				request.setAttribute("message", message);
			} catch (DAOException e) {
				e.printStackTrace();
				// リクエストスコープにエラーメッセージを設定
				request.setAttribute("error", e.getMessage());
			}
		} else if (action.equals("updateEntry")) {
			// リクエストパラメータを取得
			String id = request.getParameter("id");
			// 遷移先URLを設定
			nextPage = DIR_JSP + "/user/updateEntry.jsp";
		}
		this.gotoPage(request, response, nextPage);
	}

}
