package dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 基底DAOクラスに対する単体テストクラス
 * @author tutor
 */
class BaseDaoTest {

	/** テスト対象クラス：system under test */
	BaseDAO sut;

	/** テスト補助変数 */
	Connection conn;

	@BeforeEach
	void setUp() throws Exception {
		conn = TestDbUtils.getConnection();
		sut = new BaseDAO(conn);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("BaseDAO#getSequenceメソッドのテストクラス")
	class getSequence {
		@BeforeEach
		void setUp() throws Exception {
			String sql = "select setval('member_id_seq', 1)";
			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				pstmt.executeQuery();
			}
		}
		@Test
		@DisplayName("【Test-01】memberテーブルに対するシーケンスを取得できる")
		void test_01() throws Exception {
			// setup
			String target = "member_id_seq";
			// execute
			int actual = sut.getSequence(target);
			// verify
			assertThat(actual, is(2));
		}
	}

}
