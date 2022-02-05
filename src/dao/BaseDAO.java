package dao;

import java.sql.Connection;

/**
 * すべてのDAOクラスが継承する基底DAOクラス
 * @author tutor
 */
public class BaseDAO {

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




}
