package y1j2x34.wechat.message.mass;

import y1j2x34.wechat.utils.MessageUtils;
/**
 * 按分组群发消息——图片消息
 * @author y1j2x34
 * @date 2014-7-3 上午10:44:14
 */
public class MassGroupImageMsg extends MassGroupMsg{

	private static final long serialVersionUID = 1L;
	{
		setMsgtype(MessageUtils.MASS_MESSAGE_TYPE_IMAGE);
	}
	private MassImage image;

	public MassImage getImage() {
		return image;
	}

	public void setImage(MassImage image) {
		this.image = image;
	}
}
