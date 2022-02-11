package bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * memberテーブルの1レコードを管理するBean
 * @author tutor
 */
public class MemberBean implements Serializable {

	/**
	 * クラスメンバ
	 */
	private int id;								// 利用者ID
	private String card;					// 利用者カード番号
	private String name;					// 利用者氏名
	private String zipcode;				// 郵便番号
	private String address;				// 住所
	private String phone;					// 電話番号
	private String email;					// 電子メールアドレス
	private Date birthday;				// 生年月日
	private int privilege;				// 権限コード
	private String password;			// パスワード
	private Timestamp signupAt;		// 登録日
	private Timestamp updatedAt;	// 更新日
	private Timestamp erasuredAt;	// 登録抹消日

	/**
	 * デフォルトコンストラクタ
	 */
	public MemberBean() {}

	/**
	 * コンストラクタ
	 * @param id				利用者ID
	 * @param card			利用者カード番号
	 * @param name			利用者氏名
	 * @param zipcode		郵便番号
	 * @param address		住所
	 * @param phone			電話番号
	 * @param email			電子メールアドレス
	 * @param birthday	生年月日
	 * @param privilege	権限コード
	 */
	public MemberBean(int id, String card, String name, String zipcode, String address, String phone, String email, Date birthday, int privilege) {
		this.id = id;
		this.card = card;
		this.name = name;
		this.zipcode = zipcode;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
		this.privilege = privilege;
	}

	/**
	 * 利用者IDを取得する。
	 * @return id 利用者ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 利用者IDを設定する。
	 * @param id 設定する利用者ID
	 */
	public void setId(int id) {
		this.id = id;
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
	 * 利用者名を取得する。
	 * @return name 利用者名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 利用者名を設定する。
	 * @param name 設定する利用者名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 郵便番号を取得する。
	 * @return zipcode 郵便番号
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * 郵便番号を設定する。
	 * @param zipcode 設定する郵便番号
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * 住所を取得する。
	 * @return address 住所
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 住所を設定する。
	 * @param address 設定する住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 電話番号を取得する。
	 * @return phone 電話番号
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 電話番号を設定する。
	 * @param phone 設定する電話番号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 電子メールアドレスを取得する。
	 * @return email 電子メールアドレス
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 電子メールアドレスを設定する。
	 * @param email 設定する電子メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 生年月日を取得する。
	 * @return birthday 生年月日
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 生年月日を設定する。
	 * @param birthday 設定する生年月日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * @param privilege 設定する権限コード
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
	 * パスワードを設定する
	 * @param password 設定するパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 登録日を取得する。
	 * @return signupAt 登録日
	 */
	public Timestamp getSignupAt() {
		return signupAt;
	}

	/**
	 * 登録日を設定する。
	 * @param signupAt 設定する登録日
	 */
	public void setSignupAt(Timestamp signupAt) {
		this.signupAt = signupAt;
	}

	/**
	 * 更新日を取得する。
	 * @return updatedAt 更新日
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 更新日を設定する。
	 * @param updatedAt 設定する更新日
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 登録抹消日を取得する。
	 * @return erasuredAt 登録抹消日
	 */
	public Timestamp getErasuredAt() {
		return erasuredAt;
	}

	/**
	 * 登録抹消日を設定する。
	 * @param erasuredAt 設定する登録抹消日
	 */
	public void setErasuredAt(Timestamp erasuredAt) {
		this.erasuredAt = erasuredAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberBean [");
		builder.append("id=" + this.id + ", ");
		builder.append("card=" + this.card + ", ");
		builder.append("name=" + this.name + ", ");
		builder.append("zipcode=" + this.zipcode + ", ");
		builder.append("address=" + this.address + ", ");
		builder.append("phone=" + this.phone + ", ");
		builder.append("email=" + this.email + ", ");
		builder.append("birthday=" + this.birthday + ", ");
		builder.append("privilege=" + this.privilege + ", ");
		builder.append("signupAt=" + this.signupAt + ", ");
		builder.append("updatedAt = " + this.updatedAt + ", ");
		builder.append("erauredAt = " + this.erasuredAt + "]");
		return builder.toString();
	}

}
