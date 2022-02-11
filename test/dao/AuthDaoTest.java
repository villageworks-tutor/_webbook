package dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import bean.AuthBean;

class AuthDaoTest extends BaseDBUnitTest {

	/**
	 * クラス定数
	 */
	private static final String PATH_DEFAULT_USERS = BaseDBUnitTest.DIR_FIXTURES + "/auth/default.xml";
	private static final String PATH_EXPECTED_INSERT = BaseDBUnitTest.DIR_FIXTURES + "/auth/expected_insert.xml";

	private static final String TARGET_TABLE = "auth";
	private static final String[] EXCLUDED_COLUMNS = {"id", "signup_at", "updated_at", "erasured_at"};

	/** テスト対象クラス：System Under Test */
	AuthDAO sut;

	@BeforeEach
	void setUp() throws Exception {
		sut = new AuthDAO(TestDbUtils.getConnection());
		// DBUit関連オブジェクトの取得
		this.createDBUnitConnections(PATH_DEFAULT_USERS);
	}
	@AfterEach
	void tearDown() throws Exception {
		// DBUit関連オブジェクトの破棄
		this.shutdownDBUnitConnection();
	}

	@Nested
	@DisplayName("AuthDAO#insertメソッドのテストクラス")
	class insert extends BaseDBUnitTest {
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
		@DisplayName("【Test-02】パスワード「abc」の利用者を登録して、「abc」でログインできる")
		void tesst_02() throws Exception {
			// setup
			String card = "12050001";
			String password = "abc";
			AuthBean target = new AuthBean(card, password);
			target.setPrivilege(2);
			// execute
			sut.insert(target);
			// verify
			assertThat(sut.getAuth(target), is(instanceOf(AuthBean.class)));
		}

		@Test
		@DisplayName("【Test-01】パスワード「abc」の利用者を登録できる")
		void test_01() throws Exception {
			// setup
			AuthBean target = new AuthBean("12050001", "abc");
			target.setPrivilege(2);
			// password: 58d8e6904b11c0d8a204b30ee33fd63342a2a7fa82951d20c7b7f0d163e04c82

			// execute
			sut.insert(target);

			// テーブルの期待値を設定
			ITable expected = super.createExpectedTable(PATH_EXPECTED_INSERT, TARGET_TABLE);
			// テーブルの実行値を取得
			ITable actual = super.createEctualTable(TARGET_TABLE, EXCLUDED_COLUMNS);

			// verify
			Assertion.assertEquals(expected, actual);
		}
	}

	@Nested
	@DisplayName("AuthDAO#getAuthメソッドのテストクラス")
	class getAuth {
		@Test
		@DisplayName("【Test-03】利用者カード「12056692」パスワード「usr1」のユーザの権限は1である")
		void test_03() throws Exception {
			// setup
			AuthBean target = new AuthBean("12056692", "usr1");
			int expected = 1;
			// execute
			int actual = sut.getAuth(target).getPrivilege();
			// verify
			assertThat(actual, is(expected));
		}

		@Test
		@DisplayName("【Test-02】利用者カード番号「12056692」パスワード「pass」のユーザはログインできない")
		void test_02() throws Exception {
			// setup
			AuthBean target = new AuthBean("12056692", "pass");
			// execute
			AuthBean actual = sut.getAuth(target);
			// verify
			assertThat(actual, is(nullValue()));
		}

		@Test
		@DisplayName("【Test-01】利用者カード「12056692」パスワード「usr1」のユーザはログインできる")
		void test_01() throws Exception {
			// setup
			String card = "12056692";
			String password = "usr1";
			AuthBean target = new AuthBean(card, password);
			// execute
			AuthBean actual = sut.getAuth(target);
			// verify
			assertThat(actual, is(notNullValue()));
		}
	}

	@Nested
	@DisplayName("AuthDAO#constructorメソッドのテストクラス")
	class construct {
		@Test
		@DisplayName("AuthDAOをインスタンス化できる")
		void test() {
			// execute
			Object actual = new AuthDAO(TestDbUtils.getConnection());
			// verify
			assertThat(actual, is(instanceOf(AuthDAO.class)));
		}
	}

}
