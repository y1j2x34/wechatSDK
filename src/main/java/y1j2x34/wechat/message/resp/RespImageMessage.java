package y1j2x34.wechat.message.resp;

import y1j2x34.wechat.utils.MessageUtils;

/**
 * 响应消息——图片消息
 */
public class RespImageMessage extends RespBaseMessage{

	private static final long serialVersionUID = -4520232338679159587L;
	private Image Image;
	public RespImageMessage() {
		setMsgType(MessageUtils.RESP_MESSAGE_TYPE_NEWS);
	}
	public Image getImage() {
		return Image;
	}
	public void setImage(Image image) {
		Image = image;
	}
}
