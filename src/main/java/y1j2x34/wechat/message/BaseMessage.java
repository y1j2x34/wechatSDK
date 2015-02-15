package y1j2x34.wechat.message;

import y1j2x34.wechat.utils.MessageUtils;
/**
 * 微信请求-响应的消息基类
 */
public abstract class BaseMessage extends Message{
	
	private static final long serialVersionUID = -1747788272924248129L;
	
	private String ToUserName;
	private String FromUserName;
	private String MsgType;
	private Long CreateTime;
	/**
	 * @return 消息接收方
	 */
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	/**
	 * @return 消息发送方
	 */
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	/**
	 * @return 消息创建时间 
	 * @see #setCreateTime(long)
	 */
	public Long getCreateTime() {
		return CreateTime;
	}
	/**
	 * @param createTime 消息创建时间（单位：s）
	 * @see {@link #getCreateTime()}
	 */
	public void setCreateTime(Long createTime) {
		this.CreateTime = createTime;
	}
	/**
	 * 消息的类型对应为：
	 * <style type="text/css">
	 * .gray{background-color:#ccc;}
	 * .white{background-color:#eee;}
	 * </style>
	 * <table border="1">
	 * <tr class="gray">
	 * 	<td>文本</td><td>text</td>
	 * </tr>
	 * <tr class="white">
	 * 	<td>图片</td><td>image</td>
	 * </tr>
	 * <tr class="gray">
	 * 	<td>地理位置</td><td>location</td>
	 * </tr>
	 * <tr class="white">
	 * 	<td>链接消息</td><td>link</td>
	 * </tr>
	 * <tr class="gray">
	 * 	<td>事件推送</td><td>event</td>
	 * </tr>
	 * </table>
	 * @return
	 * 		消息的类型
	 * @see #setMsgType(String)
	 * @see MessageUtils#REQ_MESSAGE_TYPE_TEXT
	 */
	public String getMsgType() {
		return MsgType;
	}
	/**
	 * @param msgType
	 * @see #getMsgType()
	 */
	public void setMsgType(String msgType) {
		this.MsgType = msgType;
	}
}
