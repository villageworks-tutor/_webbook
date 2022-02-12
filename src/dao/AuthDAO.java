package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AuthBean;

public class AuthDAO {

	/**
	 * クラス定数
	 */
	private static final String
		SQL_CHECK = "SELECT member.card, member.privilege "
							+ "FROM member, auth "
							+ "WHERE member.card = auth.card "
							+ "AND (auth.card = ? AND auth.password = ?)";
	private static final String
		SQL_INSERT = "INSERT INTO auth (card, password, privilege) VALUES (?, ?, ?)";
	private static final String
		SQL_DELETE = "DELETE FROM auth WHERE card= ?";

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
	 * @throws DAOException
	 */
	public AuthBean getAuth(AuthBean auth) throws DAOException {
		// SQL実行オブジェクトを取得
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_CHECK);) {
			// プレースホルダにパラメータバインド
			pstmt.setString(1, auth.getCard());
			pstmt.setString(2, auth.getPassword());
			// 戻り値の初期化
			AuthBean bean = null;
			// SQLを実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					bean = new AuthBean();
					bean.setCard(rs.getString("card"));
					bean.setPrivilege(rs.getInt("privilege"));
				}
			}
			// 戻り値を返却
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * 認証情報を登録する。
	 * @param auth 登録する認証情報
	 * @return 登録に成功した場合は1、それ以外の場合はDAO例外を送出
	 * @throws DAOException
	 */
	public int insert(AuthBean auth) throws DAOException {
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_INSERT);) {
			// プレースホルダにパラメータバインド
			pstmt.setString(1, auth.getCard());
			pstmt.setString(2, auth.getPassword());
			pstmt.setInt(3, auth.getPrivilege());
			// SQLを実行
			int row = pstmt.executeUpdate();
			// 戻り値を返却
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの挿入に失敗しました。");
		}
	}

	/**
	 * 認証情報を削除する。<br />
	 * 外部キー参照先のmemberテーブルのレコード削除の種類（論理削除・物理削除）によらず、このテーブルの削除は物理削除とする。
	 * @param auth 削除する認証情報
	 * @return 削除に成功した場合は1、それ以外の場合はDAO例外を送出
	 * @throws DAOException
	 */
	public int delete(AuthBean auth) throws DAOException {
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_DELETE)) {
			// プレースホルダにパラメータバインド
			pstmt.setString(1, auth.getCard());
			// SQLを実行
			int row = pstmt.executeUpdate();
			// 戻り値を返却
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの削除に失敗しました。");
		}
	}

}
