package y1j2x34.wechat.message.mass.prev;

import y1j2x34.wechat.message.mass.MassMPNews;
import y1j2x34.wechat.utils.MessageUtils;

public class MassPreviewNewsMsg extends MassPreviewMsg{

	private static final long serialVersionUID = 1L;
	
	private MassMPNews mpnews;
	{
		setMsgtype(MessageUtils.MASS_MESSAGE_TYPE_NEWS);
	}
	public MassMPNews getMpnews() {
		return mpnews;
	}
	public void setMpnews(MassMPNews mpnews) {
		this.mpnews = mpnews;
	}
}
