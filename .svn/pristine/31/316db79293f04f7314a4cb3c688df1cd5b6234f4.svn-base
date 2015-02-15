package y1j2x34.wechat.message.req;

import y1j2x34.wechat.utils.MessageUtils;

/**
 * 图片消息
 * <hr>
 * @author 杨建新
 * @see ReqBaseMessage
 */
public class ReqImageMessage extends ReqMediaMessage{

	private static final long serialVersionUID = -3705404552572887115L;
	
	private String PicUrl;
	public ReqImageMessage() {
		setMsgType(MessageUtils.REQ_MESSAGE_TYPE_IMAGE);
	}
	/**
	 * @return 图片链接，可以通过这个链接下载图片
	 * @see #setPicUrl(String)
	 */
	public String getPicUrl() {
		return PicUrl;
	}
	/**
	 * @param picUrl
	 * @see #getPicUrl()
	 */
	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}
	
	
}
