package y1j2x34.wechat.message.req;

import y1j2x34.wechat.utils.MessageUtils;


/**
 * 事件推送
 * <hr>
 * 只支持微信4.5版本，目前仅开启自定义菜单接口事件推送
 * @author yangjianxin
 * @see ReqBaseMessage
 */
public class ReqEventMessage extends ReqBaseMessage {

	private static final long serialVersionUID = -4263871350834668745L;
	private String Event;
	public ReqEventMessage() {
		setMsgType(MessageUtils.REQ_MESSAGE_TYPE_EVENT);
	}
	/**
	 * <ol>
	 * 事件类型
	 * <li>
	 * 	subscribe(订阅)
	 * </li>
	 * <li>
	 * unsubscribe（取消订阅）
	 * </li>
	 * <li>
	 * CLICK（自定义菜单点击事件）
	 * </li>
	 * </ol>
	 * @return 事件类型
	 * @see #setEvent(String)
	 */
	public String getEvent() {
		return Event;
	}
	/**
	 * @param event
	 * @see #getEvent()
	 */
	public void setEvent(String event) {
		this.Event = event;
	}
	/**
	 * @deprecated 事件消息没有该字段
	 */
	@Override
	public Long getMsgId() {
		return super.getMsgId();
	}
	/**
	 * @deprecated 事件消息没有该字段
	 */
	@Override
	public void setMsgId(Long msgId) {
		super.setMsgId(msgId);
	}
}
