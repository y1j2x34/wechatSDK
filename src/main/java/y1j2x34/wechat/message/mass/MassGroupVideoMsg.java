package y1j2x34.wechat.message.mass;

import y1j2x34.wechat.utils.MessageUtils;
/**
 * 按分组群发消息--视频消息
 * @author y1j2x34
 * @see MassGroupMsg
 * @date 2014-7-3 上午9:38:54
 */
public class MassGroupVideoMsg extends MassGroupMsg{

	private static final long serialVersionUID = 1L;
	{
		setMsgtype(MessageUtils.MASS_MESSAGE_TYPE_VIDEO);
	}
	private MassMPVideo mpvideo;
	public MassMPVideo getMpvideo() {
		return mpvideo;
	}
	public void setMpvideo(MassMPVideo mpvideo) {
		this.mpvideo = mpvideo;
	}
}
