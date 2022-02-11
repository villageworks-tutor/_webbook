package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AuthBean;
import bean.MemberBean;
import dao.AuthDAO;
import dao.DAOException;
import dao.DbUtils;
import dao.MemberDAO;
import jp.villageworks.core.DataUtils;
import jp.villageworks.core.DataValidator;

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
		String message = "";
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
			// セッションに登録された更新用MemberBeanを取得
			HttpSession session = request.getSession(false);
			MemberBean scopedBean = (MemberBean) session.getAttribute("member");
			if (!DataUtils.isNull(scopedBean)) {
				request.setAttribute("member", scopedBean);
				nextPage = DIR_JSP + "/user/updateEntry.jsp";
			} else {
				// リクエストパラメータを取得
				String id = request.getParameter("id");
				try {
					// 利用者IDに合致した利用者を取得
					MemberDAO dao = new MemberDAO(DbUtils.getConnection());
					MemberBean bean = dao.getByPrimaryKey(id);
					if (DataUtils.isNull(bean)) {
						// リクエストスコープにエラーメッセージを設定
						message = "該当する利用者は見つかりませんでした。";
						// 遷移先URLを設定
						nextPage = DIR_JSP + "/user/serchEntry.jsp";
					} else {
						// リクエストスコープに検索結果を設定
						request.setAttribute("member", bean);
						// 遷移先URLを設定
						nextPage = DIR_JSP + "/user/updateEntry.jsp";
					}
				} catch (DAOException e) {
					e.printStackTrace();
					// リクエストスコープにエラーメッセージを設定
					request.setAttribute("error", e.getMessage());
					// 遷移先URLを設定
					nextPage = DIR_JSP + "/error/systemerror.jsp";
				}
			}
		} else if (action.equals("updateConfirm")) {
			// リクエストパラメータを取得
			List<String> errors = new ArrayList<String>();
			String primaryKey = request.getParameter("id");
			String card = request.getParameter("card");
			String name = request.getParameter("name");
			if (DataUtils.isEmpty(name)) {
				errors.add("利用者名を入力してください。");
			}
			String zipcode = request.getParameter("zipcode");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			if (DataUtils.isEmpty(phone)) {
				errors.add("電話番号を入力してください。");
			}
			String email = request.getParameter("email");
			if (DataUtils.isEmpty(email)) {
				errors.add("電子メールアドレスを入力してください。");
			}
			String birthday = request.getParameter("birthday");
			String privilegeCode = request.getParameter("privilege");
			String signupAt = request.getParameter("signupAt");
			String updatedAt = request.getParameter("updatedAt");

			// 入力エラーがある場合
			if (errors.size() > 0) {
				// リクエストスコープにエラーメッセージを設定
				request.setAttribute("errors", errors);
				// 遷移先URLを設定
				nextPage = DIR_JSP + "/user/updateEntry.jsp";
			} else {
				// 更新用memberBeanをインスタンス化
				MemberBean user = new MemberBean(0, card, name, zipcode, address, phone, email, null, 0);
				user.setId(Integer.valueOf(primaryKey));
				user.setBirthday(Date.valueOf(birthday));
				user.setPrivilege(Integer.valueOf(privilegeCode));
				user.setSignupAt(Timestamp.valueOf(signupAt));
				user.setUpdatedAt(Timestamp.valueOf(updatedAt));

				// 更新用インスタンスをセッションに登録
				HttpSession session = request.getSession();
				session.setAttribute("member", user);
				nextPage = DIR_JSP + "/user/updateConfirm.jsp";
			}
		} else if (action.equals("updateExecute")) {
			// セッションから更新用MemberBeanインスタンスを取得
			HttpSession session = request.getSession(false);
			MemberBean member = (MemberBean) session.getAttribute("member");
			if (DataUtils.isNull(member)) {
				request.setAttribute("message", "最初から操作し直してください。");
				nextPage = DIR_JSP + "/user/login.jsp";
			} else {
				try {
					// MemberDAOをインスタンス化
					MemberDAO dao = new MemberDAO(DbUtils.getConnection());
					dao.update(member);
					// セッションからmemberキーを破棄
					session.removeAttribute("member");
					// 更新用MemberBeanをスコープに登録
					request.setAttribute("member", member);
					// 遷移先を設定
					nextPage = DIR_JSP + "/user/updateComplete.jsp";
				} catch (DAOException e) {
					e.printStackTrace();
					// リクエストスコープにエラーメッセージを設定
					request.setAttribute("error", e.getMessage());
					// 遷移先URLを設定
					nextPage = DIR_JSP + "/error/systemerror.jsp";
				}
			}
		} else if (action.equals("insertEntry")) {
			HttpSession session = request.getSession(false);
			MemberBean scopedBean = (MemberBean) session.getAttribute("member");
			if (!DataUtils.isNull(scopedBean)) {
				request.setAttribute("member", scopedBean);
			}
			nextPage = DIR_JSP + "/user/insertEntry.jsp";
		} else if (action.equals("insertConfirm")) {
			// エラーメッセージリストを初期化
			List<String> errors = new ArrayList<String>();
			// リクエストパラメータを取得する
			String name = request.getParameter("name");
			if (DataUtils.isEmpty(name)) {
				errors.add("利用者名を入力してください。");
			}
			String password = request.getParameter("password");
			if (!DataValidator.isRequired(password)) {
				errors.add("パスワードを入力してください。");
			}
			String zipcode = request.getParameter("zipcode");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			if (DataUtils.isEmpty(phone)) {
				errors.add("電話番号を入力してください。");
			}
			String email = request.getParameter("email");
			if (DataUtils.isEmpty(email)) {
				errors.add("電子メールドレスを入力してください。");
			}
			String birthday = request.getParameter("birthday");
			String privilege = request.getParameter("privilege");

			if (errors.size() > 0) {
				MemberBean member = new MemberBean(0, "", name, zipcode, address, phone, email, Date.valueOf(birthday), 0);
				request.setAttribute("errors", errors);
				request.setAttribute("member", member);
				nextPage = DIR_JSP + "/user/insertEntry.jsp";
			} else {
				HttpSession session = request.getSession();
				if (DataUtils.isNull(session)) {
					request.setAttribute("message", "正しい操作をしてください。");
					nextPage = DIR_JSP + "/systemerror.jsp";
				} else {
					MemberBean member = (MemberBean) session.getAttribute("member");
					if (DataUtils.isNull(member)) {
						member = new MemberBean();
						session.setAttribute("member", member);
					}
					member.setName(name);
					member.setPassword(password);
					member.setZipcode(zipcode);
					member.setAddress(address);
					member.setPhone(phone);
					member.setEmail(email);
					member.setBirthday(Date.valueOf(birthday));
					member.setPrivilege(Integer.parseInt(privilege));
					nextPage = DIR_JSP + "/user/insertConfirm.jsp";
				}
			}
		} else if (action.equals("insertExecute")) {
			// セッションを取得
			HttpSession session = request.getSession(false);
			if (DataUtils.isNull(session)) {
				request.setAttribute("message", "セッションが切れています。ログインからやり直してください。");
				nextPage = DIR_JSP + "login.jsp";
			} else {
				MemberBean member = (MemberBean) session.getAttribute("member");
				if (DataUtils.isNull(member)) {
					session.removeAttribute("auth");
					session.invalidate();
					request.setAttribute("message", "正しく操作してください。");
					nextPage = DIR_JSP + "systemerror.jsp";
				} else {
					try {
						// データベースに接続
						Connection conn = DbUtils.getConnection();

						// 利用者情報の登録
						MemberDAO dao = new MemberDAO(conn);
						// 新規利用者IDを取得
						int id = dao.getSequence("member_id_seq");
						// 新規利用者番号を生成
						String card = this.createCardNumber(id);
						// 新規登録インスタンスに新規利用者IDと新規利用者番号を設定
						member.setId(id);
						member.setCard(card);
						// 新規登録を実行
						dao.insert(member);

						// 認証情報を登録
						AuthBean auth = new AuthBean();
						auth.setCard(member.getCard());
						auth.setPrivilege(member.getPrivilege());
						auth.setPassword(member.getPassword(), card);
						AuthDAO authDao = new AuthDAO(conn);
						authDao.insert(auth);
						// 利用者インスタンスのパスワードを初期化
						member.setPassword("");

						// 画面遷移
						request.setAttribute("member", member);
						nextPage = DIR_JSP + "/user/insertComplete.jsp";
					} catch (DAOException e) {
						e.printStackTrace();
						// リクエストスコープにエラーメッセージを設定
						request.setAttribute("error", e.getMessage());
						// 遷移先URLを設定
						nextPage = DIR_JSP + "/error/systemerror.jsp";
					}
				}
			}
		}
		this.gotoPage(request, response, nextPage);
	}

	/**
	 * 利用者IDから利用者カード番号を生成する。
	 * @param id 利用者ID
	 * @return 利用者カード番号
	 */
	private String createCardNumber(int id) {
		String uid_prefix = "1205";
		return uid_prefix + DataUtils.zeroPadding(4, id);
	}

}
