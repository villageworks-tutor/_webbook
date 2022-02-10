package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * すべてのDAOクラスが継承する基底DAOクラス
 * @author tutor
 */
public class BaseDAO {

	/**
	 * クラス定数
	 */
	private static final String SQL_GET_SEQUENCE = "SELECT nextval('@sequence')";
	private static final String SEQUENCE_PLACEHOLDER = "@sequence";

	/**
	 * クラスメンバ
	 */
	protected Connection conn;	// データベース接続オブジェクト

	/**
	 * コンストラクタ
	 * @param conn データベース接続オブジェクト
	 */
	protected BaseDAO(Connection conn) {
		this.conn = conn;
	}

	/**
	 * シーケンスを取得する。
	 * @param seqName シーケンス名
	 * @return シーケンス番号
	 * @throws DAOException
	 */
	public int getSequence(String seqName) throws DAOException {
		String sql = SQL_GET_SEQUENCE.replace(SEQUENCE_PLACEHOLDER, seqName);
		try (
				PreparedStatement pstmt = this.conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			int sequence = 0;
			if (rs.next()) {
				sequence = rs.getInt(1);
			}
			return sequence;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("シーケンスの取得に失敗しました。");
		}
	}

}
