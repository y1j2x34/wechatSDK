package y1j2x34.wechat.message.mass.prev;

import y1j2x34.wechat.message.mass.MassMPVideo;
import y1j2x34.wechat.utils.MessageUtils;

public class MassPreviewVideoMsg extends MassPreviewMsg{
	private static final long serialVersionUID = 1L;
	private MassMPVideo mpvideo;
	{
		setMsgtype(MessageUtils.MASS_MESSAGE_TYPE_VIDEO);
	}
	public MassMPVideo getMpvideo() {
		return mpvideo;
	}
	public void setMpvideo(MassMPVideo mpvideo) {
		this.mpvideo = mpvideo;
	}
}
