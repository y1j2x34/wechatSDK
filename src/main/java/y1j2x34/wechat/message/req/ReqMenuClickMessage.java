package y1j2x34.wechat.message.req;
/**
 * 自定义菜单点击事件(click,view)
 * @author yangjianxin
 *
 */
public class ReqMenuClickMessage extends ReqEventMessage{
	private static final long serialVersionUID = -4206546347081432804L;
	/**
	 * 在自定义菜单中无此变量
	 * deprecated at 2014-3-11
	 */
	@Deprecated
	@Override
	public Long getMsgId() {
		return super.getMsgId();
	}
	private String EventKey;
	/**
	 * @return
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 * @see #getEvent()
	 * @see #setEventKey(String)
	 */
	public String getEventKey() {
		return EventKey;
	}
	/**
	 * @param eventKey 事件KEY值
	 * @see #getEventKey()
	 */
	public void setEventKey(String eventKey) {
		this.EventKey = eventKey;
	}
}
