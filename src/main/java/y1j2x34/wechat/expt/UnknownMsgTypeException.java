package y1j2x34.wechat.expt;
/**
 * 未知消息类型异常，接收到微信服务器消息时，得到了一个未知的消息类型，可以抛出该异常。
 * 一般不会出现这种情况，除非微信接口更新。
 * 由应用选择抛出
 * @author 杨建新
 */
public class UnknownMsgTypeException extends Exception{

	private static final long serialVersionUID = 6277132863370193277L;

	public UnknownMsgTypeException() {
		super();
	}

	public UnknownMsgTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownMsgTypeException(String message) {
		super(message);
	}

	public UnknownMsgTypeException(Throwable cause) {
		super(cause);
	}
}
