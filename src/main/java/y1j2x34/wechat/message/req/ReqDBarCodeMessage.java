package y1j2x34.wechat.message.req;

import y1j2x34.wechat.utils.MessageUtils;

/**
 * 扫描带参数二维条码事件<br/>
 * 详见<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E6%94%B6%E4%BA%8B%E4%BB%B6%E6%8E%A8%E9%80%81">微信公众平台开发文档——接收事件推送</a>
 * <br/>
 * <ol > 用户扫描带场景值二维码时，可能推送以下两种事件：
	<li>如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。</li>
	<li>如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。</li>
  </ol>
  <ol>
  	两种事件
  	<li>
  		用户未关注时：
  		<br/>
  		Event: 'subscript' @see {@link MessageUtils#EVENT_TYPE_SUBSCRIBE}<br/>
  		EventKey: qrscene_为前缀，后面为二维码的参数值
  	</li>
  	<li>用户已关注时的事件推送:
  		<br/>
  		Event:'SCAN' @see {@link MessageUtils#EVENT_TYPE_SCAN}<br/>
  		EventKey: 32位无符号整数，即创建二维码时的二维码scene_id
  	</li>
  </ol>
 * <hr/>
 * @author 杨建新
 *
 */
public class ReqDBarCodeMessage extends ReqEventMessage{

	private static final long serialVersionUID = -6375296334668700400L;
	
	private String Ticket;
	
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
	
	/**
	 * 二维码的ticket，可用来换取二维码图片
	 * @return
	 */
	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	
}
