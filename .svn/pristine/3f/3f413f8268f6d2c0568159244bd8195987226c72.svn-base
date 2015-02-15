package y1j2x34.wechat.message.req;

import y1j2x34.wechat.utils.MessageUtils;

/**
 * 语音消息
 * <hr/>
 * @author 杨建新
 * @see ReqBaseMessage
 * @see ReqMediaMessage
 */
public class ReqVoiceMessage extends ReqMediaMessage{

	private static final long serialVersionUID = -655838341999698934L;
	/**
	 * 语音格式：amr,speex等
	 */
	private String Format;
	public ReqVoiceMessage() {
		setMsgType(MessageUtils.REQ_MESSAGE_TYPE_VOICE);
	}
	/**
	 * @return 语音格式
	 * @see #setFormat(String)
	 */
	public String getFormat() {
		return Format;
	}
	/**
	 * @param format
	 * @see #getFormat()
	 */
	public void setFormat(String format) {
		this.Format = format;
	}
}
