package y1j2x34.wechat.message.mass;

import y1j2x34.wechat.utils.MessageUtils;

/**
 * 根据openid列表群发消息--文本消息
 * @author y1j2x34
 */
public class MassOPListTextMsg extends MassOPListMsg{

	private static final long serialVersionUID = 1L;
	{
		setMsgtype(MessageUtils.MASS_MESSAGE_TYPE_TEXT);
	}
	private MassText text;
	public MassText getText() {
		return text;
	}
	public void setText(MassText text) {
		this.text = text;
	}
}
