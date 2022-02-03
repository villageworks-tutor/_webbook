package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AuthBean;

public class AuthDAO {

	private static final String
		SQL_AUTH_CHECK = "SELECT member.card, member.privilege "
										+ "FROM member, auth "
										+ "WHERE member.card = auth.card "
										+ "AND (auth.card = ? AND auth.password = ?)";

	/**
	 * クラスフィールド
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param conn データベース接続オブジェクト
	 */
	public AuthDAO(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 利用者カードとパスワードの組み合わせが登録されている認証情報を取得する。
	 * @param auth 入力された利用者カード番号とパスワードが設定されたAuthBeanのインスタンス
	 * @return 認証クラスのインスタンス（ただし利用者カード番号飲みが設定される）
	 */
	public AuthBean getAuth(AuthBean auth) {
//		String sql = "select * from auth where card = ? and password = ?";
//		sql = "select member.card, member.privilege from member, auth where member.card = auth.card and (auth.card = ? and auth.password = ?)";
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_AUTH_CHECK);) {
			pstmt.setString(1, auth.getCard());
			pstmt.setString(2, auth.getPassword());
			AuthBean bean = null;
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					bean = new AuthBean();
					bean.setCard(rs.getString("card"));
					bean.setPrivilege(rs.getInt("privilege"));
				}
			}
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
