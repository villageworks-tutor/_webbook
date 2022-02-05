package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MemberBean;

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
								+ "ORDER BY card";
	private static final String
	SQL_GET_BY_PRIMARY_KEY = "SELECT id, card, name, zipcode, address, phone, email, birthday, privilege "
												 + "FROM member "
												 + "WHERE id = ?";
	private static final String
		SQL_GET_BY_EMAIL = "SELECT id, card, name, zipcode, address, phone, email, birthday, privilege "
										 + "FROM member "
										 + "WHERE email = ?";

	/**
	 * コンストラクタ
	 * @param conn データベース接続オブジェクト
	 */
	protected MemberDAO(Connection conn) {
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

}
