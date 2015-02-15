package y1j2x34.wechat.expt;
/**
 * 微信开发者凭据类初始化失败异常
 * @author 杨建新
 */
public class CredentialInitializationException extends RuntimeException{

	private static final long serialVersionUID = 8698278690871075091L;

	public CredentialInitializationException() {
		super();
	}

	public CredentialInitializationException(String message) {
		super(message);
	}

	public CredentialInitializationException(Throwable cause) {
		super(cause);
	}
}
