package y1j2x34.wechat.message.req;


import y1j2x34.wechat.message.BaseMessage;

/**
 * 请求消息基类（用户 -> 公众账号）
 * @author 杨建新 
 * @date 2013-12-25
 * @version 0.01
 * @see ReqTextMessage
 * @see ReqImageMessage
 * @see ReqLinkMessage
 * @see ReqLocationMessage
 * @see ReqVoiceMessage
 */
public class ReqBaseMessage extends BaseMessage{
	
	private static final long serialVersionUID = -2113154756873619164L;
	private Long MsgId;
	/**
	 * @see {@linkplain #setToUserName(String)}
	 * @return
	 *  开发者微信号
	 */
	public String getToUserName() {
		return super.getToUserName();
	}
	/**
	 * @param toUserName 开发者微信号
	 * @see #getToUserName()
	 */
	public void setToUserName(String toUserName) {
		super.setToUserName(toUserName);
	}
	/**
	 * @param fromUserName 发送方账号（一个OpenID）
	 * @see #setFromUserName(String)
	 */
	public String getFromUserName() {
		return super.getFromUserName();
	}
	/**
	 * @param fromUserName 发送方账号
	 * @see #getFromUserName()
	 */
	public void setFromUserName(String fromUserName) {
		super.setFromUserName(fromUserName);
	}
	
	/**
	 * @return 消息ID，64位
	 * @see #setMsgId(long)
	 */
	public Long getMsgId() {
		return MsgId;
	}
	/**
	 * @param msgId 消息ID
	 * @see #getMsgId()
	 */
	public void setMsgId(Long msgId) {
		this.MsgId = msgId;
	}
}
