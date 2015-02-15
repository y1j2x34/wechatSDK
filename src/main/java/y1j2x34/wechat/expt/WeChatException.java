package y1j2x34.wechat.expt;

import y1j2x34.wechat.AppContext;
import y1j2x34.wechat.utils.WeChatUtil;

/**
 * 微信异常，单微信服务器返回错误信息时，抛出该异常
 * @author 杨建新
 */
public class WeChatException extends Exception{

	private static final long serialVersionUID = 6837280089205801657L;
	private final int merrcode;
	private Object original;
	public WeChatException() {
		super();
		merrcode = 0;
	}
	/**
	 * 错误码
	 * @see #getErrorCode()
	 * @see WeChatUtil
	 * @param errcode
	 */
	public WeChatException(int errcode,Object original) {
		super(AppContext.getInstance().getWechatErrorMessage(errcode));
		merrcode = errcode;
		this.original = original;
	}

	public WeChatException(Throwable cause) {
		super(cause);
		merrcode = 0;
	}
	/**
	 * 微信错误码,可以根据错误码取得对应的信息
	 * @see AppContext#getWechatErrorMessage(int)
	 * @see #getMessage()
	 * @see #WechatException(int)
	 * @return
	 */
	public int getErrorCode(){
		return merrcode;
	}
	public Object getOriginal() {
		return original;
	}
}
