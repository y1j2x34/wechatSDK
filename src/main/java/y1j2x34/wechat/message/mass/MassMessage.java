package y1j2x34.wechat.message.mass;

import y1j2x34.wechat.message.Message;
import y1j2x34.wechat.utils.MessageUtils;
/**
 * 群发消息<br/>
 * 消息类型:{@link #setMsgtype(String) msgtype}
 * @author y1j2x34
 * @date 2014-7-3 上午9:50:20
 */
public class MassMessage extends Message{

	private static final long serialVersionUID = 1L;
	
	private String msgtype;

	public String getMsgtype() {
		return msgtype;
	}
	/**
	 * 消息类型
	 * <table border="1">
	 * 	<thead>
	 * <tr>
	 * 	<th>消息类型</th>
	 * 	<th>封装类</th>
	 * </tr>
	 * </thead>
	 * <tbody>
	 * 	<tr>
	 * 		<td>{@linkplain MessageUtils#MASS_MESSAGE_TYPE_TEXT}</td>
	 * 		<td>{@linkplain MassGroupTextMsg}</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>{@linkplain MessageUtils#MASS_MESSAGE_TYPE_IMAGE}</td>
	 * 		<td>{@linkplain MassGroupImageMsg}</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>{@linkplain MessageUtils#MASS_MESSAGE_TYPE_VOICE}</td>
	 * 		<td>{@linkplain MassGroupVoiceMsg}</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>{@linkplain MessageUtils#MASS_MESSAGE_TYPE_VIDEO}</td>
	 * 		<td>{@linkplain MassGroupVideoMsg}</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>{@linkplain MessageUtils#MASS_MESSAGE_TYPE_NEWS}</td>
	 * 		<td>{@linkplain MassGroupNewsMsg}</td>
	 * 	</tr>
	 * 	
	 * </tbody>
	 * </table>
	 * @param msgtype
	 */
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
}
