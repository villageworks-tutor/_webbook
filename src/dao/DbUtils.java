package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

	/**
	 * クラス定数
	 */
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql:webbookdb";
	private static final String DB_USER = "librarian";
	private static final String DB_PASSWORD = "himitu";

	/**
	 * データベースに接続する（データベース接続オブジェクトを取得する）。
	 * @return データベース接続オブジェクト
	 */
	public static Connection getConnection() {
		try {
			Class.forName(DB_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
