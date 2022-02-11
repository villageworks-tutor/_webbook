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
	private static final String
		SQL_AUTH_INSERT = "INSERT INTO auth (card, password, privilege) VALUES (?, ?, ?)";

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
	 * 認証情報を登録する。
	 * @param auth 登録する認証情報
	 * @return 登録に成功した場合は1、それ以外の場合はDAO例外を送出
	 * @throws DAOException
	 */
	public int insert(AuthBean auth) throws DAOException {
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_AUTH_INSERT);) {
			pstmt.setString(1, auth.getCard());
			pstmt.setString(2, auth.getPassword());
			pstmt.setInt(3, auth.getPrivilege());
			int row = pstmt.executeUpdate();
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの挿入に失敗しました。");
		}
	}

	/**
	 * 利用者カードとパスワードの組み合わせが登録されている認証情報を取得する。
	 * @param auth 入力された利用者カード番号とパスワードが設定されたAuthBeanのインスタンス
	 * @return 認証クラスのインスタンス（ただし利用者カード番号飲みが設定される）
	 * @throws DAOException
	 */
	public AuthBean getAuth(AuthBean auth) throws DAOException {
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
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}
