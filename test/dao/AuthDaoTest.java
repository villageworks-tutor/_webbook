package dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import bean.AuthBean;

class AuthDaoTest {

	/** テスト対象クラス：System Under Test */
	AuthDAO sut;

	@BeforeEach
	void setUp() throws Exception {
		sut = new AuthDAO(TestDbUtils.getConnection());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("AuthDAO#getAuthメソッドのテストクラス")
	class getAuth {
		@Test
		@DisplayName("【Test-03】利用者カード「12056692」パスワード「usr1」のユーザの権限は1である")
		void test_03() {
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
		void test_02() {
			// setup
			AuthBean target = new AuthBean("12056692", "pass");
			// execute
			AuthBean actual = sut.getAuth(target);
			// verify
			assertThat(actual, is(nullValue()));
		}

		@Test
		@DisplayName("【Test-01】利用者カード「12056692」パスワード「usr1」のユーザはログインできる")
		void test_01() {
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
