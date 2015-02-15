package y1j2x34.wechat.message.req;
/**
 * 关注和取消关注事件
 * <hr/>
 * @author 杨建新
 */
public class ReqSubscribeMessage extends ReqEventMessage{
	private static final long serialVersionUID = 2560820727966713802L;
	private String Event;
	/**
	 * 事件类型 subscribe（订阅），unsubscribe（取消订阅）
	 * @return
	 */
	public String getEvent() {
		return Event;
	}
	/**
	 * 事件类型（订阅或取消订阅两种）
	 * @see #getEvent()
	 * @param event
	 */
	public void setEvent(String event) {
		this.Event = event;
	}
}
