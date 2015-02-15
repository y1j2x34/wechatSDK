package y1j2x34.wechat.message.resp;

import y1j2x34.wechat.utils.MessageUtils;

/**
 * 文本消息
 * <hr/>
 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E8%A2%AB%E5%8A%A8%E5%93%8D%E5%BA%94%E6%B6%88%E6%81%AF#.E5.9B.9E.E5.A4.8D.E6.96.87.E6.9C.AC.E6.B6.88.E6.81.AF">发送被动响应消息#回复文本消息</a>
 * <hr/>
 * @author yangjianxin
 * @see RespBaseMessage
 */
public class RespTextMessage extends RespBaseMessage {

	private static final long serialVersionUID = -7985288435912179542L;
	private String Content;
	public RespTextMessage() {
		setMsgType(MessageUtils.RESP_MESSAGE_TYPE_TEXT);
	}
	/**
	 * @return 回复的消息内容(<font color="green">长度不超过</font><font color="blue">2048</font><font color="red">字节</font>!)
	 * @see #setContent(String)
	 */
	public String getContent() {
		return Content;
	}
	/**
	 * @param content
	 * @see #getContent()
	 */
	public void setContent(String content) {
		this.Content = content;
	}
}
