package dao.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import bean.MemberBean;

/**
 * MemberBeanに対するカスタムMatcherクラス
 * @author tutor
 */
public class EqualToMember extends TypeSafeMatcher<MemberBean> {

	/** 期待値 */
	private MemberBean expected;

	/** 異なる値 */
	private String difference;

	/**
	 * コンストラクタ
	 * @param expected 期待値
	 */
	public EqualToMember(MemberBean expected) {
		this.expected = expected;
	}

	@Override
	public void describeTo(Description description) {
		description.appendValue(this.expected);
		description.appendText(this.difference).appendText("が異なっています。");
	}

	@Override
	protected boolean matchesSafely(MemberBean actual) {
		// 利用者IDはテスト状況に左右され、また単にインクリメントされるフィールドなので検査対象としない
		// 利用者カード番号の一致検査
		if (!actual.getCard().equals(expected.getCard())) {
			difference = "利用者カード番号";
			return false;
		}
		// 利用者氏名の一致検査
		if (!actual.getName().equals(expected.getName())) {
			difference = "利用者氏名";
			return false;
		}
		// 郵便番号の一致検査
		if (!actual.getZipcode().equals(expected.getZipcode())) {
			difference = "郵便番号";
			return false;
		}
		// 住所の一致検査
		if (!actual.getAddress().equals(expected.getAddress())) {
			difference = "住所";
			return false;
		}
		// 電話番号の一致検査
		if (!actual.getPhone().equals(expected.getPhone())) {
			difference = "電話番号";
			return false;
		}
		// 電子メールアドレスの一致検査
		if (!actual.getEmail().equals(expected.getEmail())) {
			difference = "電子メールアドレス";
			return false;
		}
		// 生年月日の一致検査
		if (!actual.getBirthday().equals(expected.getBirthday())) {
			difference = "生年月日";
			return false;
		}
		// 権限コードの一致検査
		if (actual.getPrivilege() != expected.getPrivilege()) {
			difference = "権限コード";
			return false;
		}
		// すべての検査をパスした場合はtrue！
		return true;
	}

}
