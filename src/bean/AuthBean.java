package bean;

import java.io.Serializable;

import util.PasswordUtil;

public class AuthBean implements Serializable {

	/**
	 * クラスフィールド
	 */
	private String card;			// 利用者カード番号
	private int privilege;		// 権限コード
	private String password;	// パスワード

	/**
	 * デフォルトコンストラクタ
	 */
	public AuthBean() {}

	/**
	 * コンストラクタ
	 * @param card 利用者カード番号
	 * @param password パスワード
	 */
	public AuthBean(String card, String password) {
		super();
		this.card = card;
		this.password = PasswordUtil.getHashdPassword(password, card);
	}



	/**
	 * 利用者カード番号を取得する。
	 * @return card 利用者カード番号
	 */
	public String getCard() {
		return card;
	}

	/**
	 * 利用者カード番号を設定する。
	 * @param card 設定する利用者カード番号
	 */
	public void setCard(String card) {
		this.card = card;
	}

	/**
	 * 権限コードを取得する。
	 * @return privilege 権限コード
	 */
	public int getPrivilege() {
		return privilege;
	}

	/**
	 * 権限コードを設定する。
	 * @param privilege 設定する権限
	 */
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

	/**
	 * パスワードを取得する。
	 * @return password パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定する。
	 * @param password 設定するパスワード
	 */
	public void setPassword(String password, String card) {
		this.password = PasswordUtil.getHashdPassword(password, card);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthBean [");
		builder.append("card=" + this.card + ", ");
		builder.append("privilege" + this.privilege + ", ");
		builder.append("password=" + this.password + "]");
		return builder.toString();
	}





}
