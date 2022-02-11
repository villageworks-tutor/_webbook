package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.MemberBean;
import jp.villageworks.core.DataUtils;

/**
 * 利用者に関するレコード操作を担当するDAO
 * @author tutor
 */
public class MemberDAO extends BaseDAO {

	/**
	 * クラス定数
	 */
	private static final String
		SQL_GET_ALL = "SELECT id, card, name, zipcode, address, phone, email, birthday, privilege "
								+ "FROM member "
								+ "WHERE erasured_at IS null "
								+ "ORDER BY card";
	private static final String
	SQL_GET_BY_PRIMARY_KEY = "SELECT id, card, name, zipcode, address, phone, email, birthday, privilege, signup_at, updated_at, erasured_at "
												 + "FROM member "
												 + "WHERE id = ? AND erasured_at IS null ";
	private static final String
		SQL_GET_BY_EMAIL = "SELECT id, card, name, zipcode, address, phone, email, birthday, privilege "
										 + "FROM member "
										 + "WHERE email = ? AND erasured_at IS null ";
	private static final String
		SQL_UPDATE = "UPDATE member "
							 + "SET name = ?, zipcode = ?, address = ?, phone = ?, email = ?, birthday = ?, privilege = ?, updated_at = ? "
							 + "WHERE id = ?";
	private static final String
		SQL_INSERT = "INSERT INTO member (id, card, name, zipcode, address, phone, email, birthday, privilege) "
							 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String
		SQL_DELETE = "DELETE FROM member "
							 + "WHERE id = ?";
	private static final String
		SQL_REMOVE = "UPDATE member "
							 + "SET erasured_at = ?"
							 + "WHERE id = ?";

	/**
	 * コンストラクタ
	 * @param conn データベース接続オブジェクト
	 */
	public MemberDAO(Connection conn) {
		super(conn);
	}

	/**
	 * memberテーブルのすべての利用者を取得する。
	 * @return List<MemberBean> 利用者リスト
	 * @throws DAOException
	 */
	public List<MemberBean> getAll() throws DAOException {
		try (
				PreparedStatement pstmt = this.conn.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pstmt.executeQuery();) {
			// 結果セットから戻り値を生成して返却
			return this.createListFromResultset(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * 指定された利用者ID（主キー）に該当する利用者を取得する。
	 * @param key 文字列型の利用者ID
	 * @return MemberBean 該当する場合はMemberBeanクラスのインスタンス、それ以外はnull
	 * @throws DAOException
	 */
	public MemberBean getByPrimaryKey(String key) throws DAOException {
		try {
			int id = Integer.parseInt(key);
			return this.getByPrimaryKey(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new DAOException("システムエラーが発生しました。システム管理者に問い合わせてください。");
		}
	}

	/**
	 * 指定された利用者ID（主キー）に該当する利用者を取得する。
	 * @param id 利用者ID
	 * @return MemberBean 該当する場合はMemberBeanクラスのインスタンス、それ以外はnull
	 * @throws DAOException
	 */
	public MemberBean getByPrimaryKey(int id) throws DAOException {
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_GET_BY_PRIMARY_KEY);) {
			// プレースホルダへのパラメータバインド
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery();) {
				// 結果セットから返却リストを生成して返却
				return this.createBeanFromResultset(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * 指定された電子メールアドレスが登録された利用者を取得する。
	 * @param email 電子メールアドレス
	 * @return List<MemberBean> 利用者リスト
	 * @throws DAOException
	 * TODO：利用者リストを戻り値にしているが、テーブル定義において電子メールドレスに一意性制約を設定することでリストではなくインスタンスに変更することができる。
	 */
	public List<MemberBean> getByEmail(String email) throws DAOException {
		// 引数がnullまたは空文字列の場合は全件検索
		if (DataUtils.isNull(email) || DataUtils.isEmpty(email)) {
			return this.getAll();
		}
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_GET_BY_EMAIL)) {
			// プレースホルダへのパラメータバインド
			pstmt.setString(1, email);
			try (ResultSet rs = pstmt.executeQuery()) {
				// 結果セットから返却リストを生成して返却
				return this.createListFromResultset(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * 結果セットから利用者インスタンスを生成する。
	 * @param rs 結果セット
	 * @return MemberBean 利用者インスタンス
	 * @throws SQLException
	 */
	private MemberBean createBeanFromResultset(ResultSet rs) throws SQLException {
		// 戻り値の初期化
		MemberBean bean = null;
		// 結果セットから返却リストを生成
		if (rs.next()) {
			bean = new MemberBean();
			bean.setId(rs.getInt("id"));
			bean.setCard(rs.getString("card"));
			bean.setName(rs.getString("name"));
			bean.setZipcode(rs.getString("zipcode"));
			bean.setAddress(rs.getString("address"));
			bean.setPhone(rs.getString("phone"));
			bean.setEmail(rs.getString("email"));
			bean.setBirthday(rs.getDate("birthday"));
			bean.setPrivilege(rs.getInt("privilege"));
			bean.setSignupAt(rs.getTimestamp("signup_at"));
			bean.setUpdatedAt(rs.getTimestamp("updated_at"));
			bean.setErasuredAt(rs.getTimestamp("erasured_at"));
		}
		// 戻り値を返却
		return bean;
	}

	/**
	 * 結果セットから利用者リストを生成する。
	 * @param rs 結果セット
	 * @return List<MemberBean> 利用者リスト
	 * @throws SQLException
	 */
	private List<MemberBean> createListFromResultset(ResultSet rs) throws SQLException {
		// 戻り値の初期化
		List<MemberBean> list = new ArrayList<MemberBean>();
		// 結果セットから返却リストを生成
		while (rs.next()) {
			MemberBean bean = new MemberBean();
			bean.setId(rs.getInt("id"));
			bean.setCard(rs.getString("card"));
			bean.setName(rs.getString("name"));
			bean.setZipcode(rs.getString("zipcode"));
			bean.setAddress(rs.getString("address"));
			bean.setPhone(rs.getString("phone"));
			bean.setEmail(rs.getString("email"));
			bean.setBirthday(rs.getDate("birthday"));
			bean.setPrivilege(rs.getInt("privilege"));
			list.add(bean);
		}
		// 戻り値を返却
		return list;
	}

	/**
	 * 利用者の項目を更新する。
	 * @param member 更新するMembereanのインスタンス
	 * @return 更新が成功した場合は1が返されるが、失敗した場合は例外が送出される。
	 * @throws DAOException
	 */
	public int update(MemberBean member) throws DAOException {
		// 現在日時を取得
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		member.setUpdatedAt(currentTimestamp);
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_UPDATE);) {
			// プレースホルダへのパラメータバインド
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getZipcode());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setDate(6, member.getBirthday());
			pstmt.setInt(7, member.getPrivilege());
			pstmt.setTimestamp(8, member.getUpdatedAt());
			pstmt.setInt(9, member.getId());
			// SQLを実行と成功した号数を取得
			int row = pstmt.executeUpdate();
			// 行数を返却
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの更新に失敗しました。");
		}
	}

	/**
	 * 利用者を登録する。
	 * @param member 登録するMembereanのインスタンス
	 * @return 登録が成功した場合は1が返されるが、失敗した場合は例外が送出される。
	 * @throws DAOException
	 */
	public int insert(MemberBean member) throws DAOException {
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_INSERT)) {
			// プレースホルダへのパラメータバインド
			pstmt.setInt(1, member.getId());
			pstmt.setString(2, member.getCard());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getZipcode());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.setDate(8, member.getBirthday());
			pstmt.setInt(9, member.getPrivilege());
			// SQLを実行と成功した号数を取得
			int row = pstmt.executeUpdate();
			// 行数を返却
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの挿入に失敗しました。");
		}
	}

	/**
	 * 利用者を物理削除する。
	 * @param member 削除するMembereanのインスタンス
	 * @return 削除が成功した場合は1が返されるが、失敗した場合は例外が送出される。
	 * @throws DAOException
	 */
	public int delete(MemberBean member) throws DAOException {
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_DELETE)) {
			// プレースホルダへのパラメータバインド
			pstmt.setInt(1, member.getId());
			// SQLを実行と成功した号数を取得
			int row = pstmt.executeUpdate();
			// 行数を返却
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの物理削除に失敗しました。");
		}
	}

	/**
	 * 利用者を論理削除する。
	 * @param member 削除するMembereanのインスタンス
	 * @return 削除が成功した場合は1が返されるが、失敗した場合は例外が送出される。
	 * @throws DAOException
	 */
	public int remove(MemberBean member) throws DAOException {
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_REMOVE)) {
			// プレースホルダへのパラメータバインド
			pstmt.setTimestamp(1, member.getErasuredAt());
			pstmt.setInt(2, member.getId());
			// SQLを実行と成功した号数を取得
			int row = pstmt.executeUpdate();
			// 行数を返却
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの論理削除に失敗しました。");
		}
	}

}
