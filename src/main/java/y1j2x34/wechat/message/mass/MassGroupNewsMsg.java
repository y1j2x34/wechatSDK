package y1j2x34.wechat.message.mass;

import y1j2x34.wechat.utils.MessageUtils;
/**
 * 按分组群发消息——图文消息
 * @author y1j2x34
 * @date 2014-7-3 上午9:38:20
 */
public class MassGroupNewsMsg extends MassGroupMsg{

	private static final long serialVersionUID = 1L;
	{
		setMsgtype(MessageUtils.MASS_MESSAGE_TYPE_NEWS);
	}
	private MassMPNews mpnews;
	public MassMPNews getMpnews() {
		return mpnews;
	}
	public void setMpnews(MassMPNews news) {
		this.mpnews = news;
	}
}
