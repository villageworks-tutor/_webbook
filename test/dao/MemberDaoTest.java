package dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import bean.MemberBean;
import dao.matcher.EqualToMember;

@SuppressWarnings("unused")
/**
 * 利用者に関するレコード操作に対する単体テストクラス
 * @author tutor
 */
class MemberDaoTest {

	/**
	 * クラス定数
	 */
	private static final String PATH_DEFAULT_USERS = BaseDBUnitTest.DIR_FIXTURES + "/member/default_member.xml";
	private static final String PATH_EXPECTED_UPDATE_01 = BaseDBUnitTest.DIR_FIXTURES + "/member/expected_update_member_01.xml";

	private static final String TARGET_TABLE = "member";
	private static final String[] EXCLUDED_COLUMNS = {"id", "signup_at", "updated_at", "erasured_at"};

	/** テスト対象クラス：system under test */
	MemberDAO sut;

	@BeforeEach
	void setUp() throws Exception {
		// テスト対象クラスをインスタンス化
		sut = new MemberDAO(TestDbUtils.getConnection());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("● 更新系メソッドのテスト ●")
	class updates extends BaseDBUnitTest {

		@Nested
		@DisplayName("MemberDAO#updateメソッドのテストクラス")
		class update extends BaseDBUnitTest {

			@BeforeEach
			void setUp() throws Exception {
				// DBUit関連オブジェクトの取得
				this.createDBUnitConnections(PATH_DEFAULT_USERS);
			}
			@AfterEach
			void tearDown() throws Exception {
				// DBUit関連オブジェクトの破棄
				this.shutdownDBUnitConnection();
			}

			@Test
			@DisplayName("【Test-03】更新日が更新される")
			void test_03() throws Exception {
				// setup
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
				// 比較書式は「yyyy-MM-dd」とする。
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String expected = formatter.format(currentTimestamp);
				// 更新対象クラスの利用者IDと電子メールドレスを設定
				MemberBean target = new MemberBean();
				target.setId(1);
				target.setEmail("kenzo_kiyota@xcity.org");
				// 更新対象となるmemberBeanのインスタンス化
				MemberBean member = new MemberBean();
				member.setId(target.getId());
				member.setCard("12050662");
				member.setName("清田 健蔵");
				member.setZipcode("277-0851");
				member.setAddress("千葉県柏市向原町1-17-13");
				member.setPhone("080-3440-9925");
				member.setEmail(target.getEmail());
				member.setBirthday(Date.valueOf("2001-02-01"));
				member.setPrivilege(2);

				// execute
				sut.update(member);
				member = new MemberBean();
				member = sut.getByPrimaryKey(target.getId());
				String actual = formatter.format(member.getUpdatedAt());
				// verify
				assertThat(actual, is(expected));
			}

			@Test
			@DisplayName("【Test-02】未登録の利用者ID「-1」の利用者の郵便番号を「111-1111」に変更できない")
			void test_02() throws Exception {
				// setup
				String target = "111-1111";
				// 更新対象となるmemberBeanのインスタンス化
				MemberBean member = new MemberBean();
				member.setId(-1);
				member.setCard("12050662");
				member.setName("清田 健蔵");
				member.setZipcode("277-0851");
				member.setAddress("千葉県柏市向原町1-17-13");
				member.setPhone("080-3440-9925");
				member.setEmail("xxxx@xxxx.com");
				member.setBirthday(Date.valueOf("2001-02-01"));
				member.setPrivilege(2);
				int expected = 0;
				// execute
				int actual = sut.update(member);
				// verify
				assertThat(actual, is(expected));
			}

			@Test
			@DisplayName("【Test-01】登録済の利用者ID「1」の利用者の電子メールアドレスを「kenzo_kiyota@xcity.org」に更新できる")
			void test_01() throws Exception {
				// setup
				String target = "kenzo_kiyota@xcity.org";
				// 更新対象となるmemberBeanのインスタンス化
				MemberBean member = new MemberBean();
				member.setId(1);
				member.setCard("12050662");
				member.setName("清田 健蔵");
				member.setZipcode("277-0851");
				member.setAddress("千葉県柏市向原町1-17-13");
				member.setPhone("080-3440-9925");
				member.setEmail(target);
				member.setBirthday(Date.valueOf("2001-02-01"));
				member.setPrivilege(2);

				// execute
				sut.update(member);

				// テーブルの期待値を設定
				ITable expected = super.createExpectedTable(PATH_EXPECTED_UPDATE_01, TARGET_TABLE);
				// テーブルの実行値を取得
				ITable actual = super.createEctualTable(TARGET_TABLE, EXCLUDED_COLUMNS);

				// verify
				Assertion.assertEquals(expected, actual);
			}
		}
	}

	@Nested
	@DisplayName("● 参照系メソッドのテスト ●")
	class gets extends BaseDBUnitTest {

		@BeforeEach
		void setUp() throws Exception {
			// DBUit関連オブジェクトの取得
			this.createDBUnitConnections(PATH_DEFAULT_USERS);
		}
		@AfterEach
		void tearDown() throws Exception {
			// DBUit関連オブジェクトの破棄
			this.shutdownDBUnitConnection();
		}

		@Nested
		@DisplayName("MemberDAO#getByPrimaryKeyメソッドのテストクラス")
		class getByPrimaryKey {
			@Test
			@DisplayName("【Test-02】登録されていない利用者ID「-1」に該当する利用者はいない")
			void test_02() throws Exception {
				// setup
				int target = -1;
				// execute &
				MemberBean actual = sut.getByPrimaryKey(target);
				// verify
				assertThat(actual, is(nullValue()));
			}

			@Test
			@DisplayName("【Test-01】登録されている利用者ID「3」に該当する利用者は「浜口 秋雄」である")
			void test_01() throws Exception {
				// setup
				int target = 3;
				MemberBean expected = new MemberBean();
				expected.setId(target);
				expected.setCard("12058021");
				expected.setName("浜口 秋雄");
				expected.setZipcode("259-0201");
				expected.setAddress("神奈川県足柄下郡真鶴町真鶴3-20-8");
				expected.setPhone("080-4751-9498");
				expected.setEmail("fujio_tsuchiya@yfqkvmrmfr.geq.bbl");
				expected.setBirthday(Date.valueOf("1987-11-15"));
				expected.setPrivilege(2);
				// execute
				MemberBean actual = sut.getByPrimaryKey(target);
				// verify
				assertThat(actual, is(new EqualToMember(expected)));
			}
		}

		@Nested
		@DisplayName("MemberDAO#getAllメソッドのテストクラス")
		class getAll {
			@Test
			@DisplayName("【Test-01】memberテーブルの全レコードを取得できる")
			void test_01() throws Exception {
				// setup
				List<MemberBean> expected = new ArrayList<MemberBean>();
				MemberBean member = null;
				member = new MemberBean();
				member.setCard("12050662");
				member.setName("清田 健蔵");
				member.setZipcode("277-0851");
				member.setAddress("千葉県柏市向原町1-17-13");
				member.setPhone("080-3440-9925");
				member.setEmail("yuzuki086@udtmsizh.nbl.jrml");
				member.setBirthday(Date.valueOf("2001-02-01"));
				member.setPrivilege(2);
				expected.add(member);

				member = new MemberBean();
				member.setCard("12056692");
				member.setName("梅田 俊章");
				member.setZipcode("232-0016");
				member.setAddress("神奈川県横浜市南区宮元町2-16-18");
				member.setPhone("080-4293-2703");
				member.setEmail("mizuho2311@mhwuymgwsr.rp.rql");
				member.setBirthday(Date.valueOf("1985-07-05"));
				member.setPrivilege(1);
				expected.add(member);

				member = new MemberBean();
				member.setCard("12058021");
				member.setName("浜口 秋雄");
				member.setZipcode("259-0201");
				member.setAddress("神奈川県足柄下郡真鶴町真鶴3-20-8");
				member.setPhone("080-4751-9498");
				member.setEmail("fujio_tsuchiya@yfqkvmrmfr.geq.bbl");
				member.setBirthday(Date.valueOf("1987-11-15"));
				member.setPrivilege(2);
				expected.add(member);
				// execute
				List<MemberBean> actual = sut.getAll();
				// verify
				for (int i = 0; i < actual.size(); i++) {
					assertThat(actual.get(i), is(new EqualToMember(expected.get(i))));
				}
			}
		}

		@Nested
		@DisplayName("MemberDAO#getByEmailメソッドのテストクラス")
		class getByEmail {
			@Test
			@DisplayName("【Test-11：異常系】正しくないSQLを実行するとDAOExceptionが送出される")
			void test_11() {
				// setup
				TestMemberDAO dao = new TestMemberDAO(TestDbUtils.getConnection());
				String target = "sample@cbc.com";
				List<MemberBean> expected = new ArrayList<MemberBean>();
				try {
					// execute
					List<MemberBean> actual = dao.getByEmail(target);
					fail("異常系のテストは失敗しました。");
				} catch (Exception e) {
					assertThat(e, is(instanceOf(DAOException.class)));
					assertThat(e.getMessage(), is("レコードの取得に失敗しました。"));
				}
			}

			@Test
			@DisplayName("【Test-03：電子メールアドレス未入力による検索】電子メールアドレスを指定しない場合はすべての利用者を取得する")
			void tets_03() throws Exception {
				// setup
				String target = "";
				List<MemberBean> expected = new ArrayList<MemberBean>();
				MemberBean member = null;
				member = new MemberBean();
				member.setCard("12050662");
				member.setName("清田 健蔵");
				member.setZipcode("277-0851");
				member.setAddress("千葉県柏市向原町1-17-13");
				member.setPhone("080-3440-9925");
				member.setEmail("yuzuki086@udtmsizh.nbl.jrml");
				member.setBirthday(Date.valueOf("2001-02-01"));
				member.setPrivilege(2);
				expected.add(member);

				member = new MemberBean();
				member.setCard("12056692");
				member.setName("梅田 俊章");
				member.setZipcode("232-0016");
				member.setAddress("神奈川県横浜市南区宮元町2-16-18");
				member.setPhone("080-4293-2703");
				member.setEmail("mizuho2311@mhwuymgwsr.rp.rql");
				member.setBirthday(Date.valueOf("1985-07-05"));
				member.setPrivilege(1);
				expected.add(member);

				member = new MemberBean();
				member.setCard("12058021");
				member.setName("浜口 秋雄");
				member.setZipcode("259-0201");
				member.setAddress("神奈川県足柄下郡真鶴町真鶴3-20-8");
				member.setPhone("080-4751-9498");
				member.setEmail("fujio_tsuchiya@yfqkvmrmfr.geq.bbl");
				member.setBirthday(Date.valueOf("1987-11-15"));
				member.setPrivilege(2);
				expected.add(member);
				// execute
				List<MemberBean> actual = sut.getByEmail(target);
				// verify
				for (int i = 0; i < actual.size(); i++) {
					assertThat(actual.get(i), is(new EqualToMember(expected.get(i))));
				}
			}

			@Test
			@DisplayName("【Test-02：未登録電子メールアドレスによる検索】未登録電子メールアドレス「sample@abc.com」の利用者は検索されない")
			void test_02() throws Exception {
				// setup
				String target = "sample@cbc.com";
				List<MemberBean> expected = new ArrayList<MemberBean>();
				// execute
				List<MemberBean> actual = sut.getByEmail(target);
				// verify
				for (int i = 0; i < actual.size(); i++) {
					assertThat(actual.get(i), is(new EqualToMember(expected.get(i))));
				}
			}

			@Test
			@DisplayName("【Test-01：登録済電子メールアドレスによる検索】登録済電子メールアドレス「mizuho2311@mhwuymgwsr.rp.rql」の利用者は利用者カード番号「12056692」の利用者である")
			void test_01() throws Exception {
				// setup
				String target = "mizuho2311@mhwuymgwsr.rp.rql";

				MemberBean bean = new MemberBean();
				bean.setId(1);
				bean.setCard("12056692");
				bean.setName("梅田 俊章");
				bean.setZipcode("232-0016");
				bean.setAddress("神奈川県横浜市南区宮元町2-16-18");
				bean.setPhone("080-4293-2703");
				bean.setEmail("mizuho2311@mhwuymgwsr.rp.rql");
				bean.setBirthday(Date.valueOf("1985-07-05"));
				bean.setPrivilege(1);
				// 期待値リストの生成
				List<MemberBean> expected = new ArrayList<MemberBean>();
				expected.add(bean);

				// execute
				List<MemberBean> actual = sut.getByEmail(target);

				// verify
				for (int i = 0; i < actual.size(); i++) {
					assertThat(actual.get(i), is(new EqualToMember(expected.get(i))));
				}
			}

		}

		@Nested
		@DisplayName("MemberDAO#constructorメソッドのテストクラス")
		class constructor {
			@Test
			@DisplayName("【Test-01】MemberDAOをインスタンス化できる")
			void test_01() {
				// setup
				Connection target = TestDbUtils.getConnection();
				// execute
				Object actual = new MemberDAO(target);
				// verify
				assertThat(actual, is(instanceOf(MemberDAO.class)));
				assertThat(target, is(notNullValue()));
			}
		}
	}

}

/**
 * バグを含むSQLを実行するDAOクラス<br />
 * すべてのキーワードを1文字欠落したバグを含むSQLを実行して例外を送出させる。
 * @author tutor
 */
class TestMemberDAO extends MemberDAO {

	/**
	 * クラス定数
	 */
	private static final String
		SQL_GET_BY_EMAIL = "SELEC card, name, zipcode, address, phone, email, birthday, privilege "
										 + "FRO member "
										 + "WHER email = ?";

	protected TestMemberDAO(Connection conn) {
		super(conn);
	}

	public List<MemberBean> getByEmail(String email) throws DAOException {
		try (PreparedStatement pstmt = this.conn.prepareStatement(SQL_GET_BY_EMAIL)) {
			// プレースホルダへのパラメータバインド
			pstmt.setString(1, email);
			// 戻り値の初期化
			List<MemberBean> list = new ArrayList<MemberBean>();
			try (ResultSet rs = pstmt.executeQuery()) {
			}
			// 戻り値を返却
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
}
