package y1j2x34.wechat.message.mass.prev;

import y1j2x34.wechat.message.mass.MassText;
import y1j2x34.wechat.utils.MessageUtils;

public class MassPreviewTextMsg extends MassPreviewMsg{

	private static final long serialVersionUID = 1L;
	
	private MassText text;
	{
		setMsgtype(MessageUtils.MASS_MESSAGE_TYPE_TEXT);
	}
	public MassText getText() {
		return text;
	}
	public void setText(MassText text) {
		this.text = text;
	}
}
