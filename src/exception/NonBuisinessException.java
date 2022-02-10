package exception;

/**
 * システムエラー
 * @author villa
 *
 */
public class NonBuisinessException extends RuntimeException {

	/**
	 * コンストラクタ
	 * @param message
	 * @param cause
	 */
	public NonBuisinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * コンストラクタ
	 * @param message
	 */
	public NonBuisinessException(String message) {
		super(message);
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
