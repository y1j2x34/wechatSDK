package y1j2x34.wechat.message.mass;

import y1j2x34.wechat.utils.MessageUtils;
/**
 * 根据openid列表群发消息--语音消息
 * @author y1j2x34
 * @date 2014-7-3 上午10:07:43
 */
public class MassOPListVoiceMsg extends MassOPListMsg{
	private static final long serialVersionUID = 1L;
	{
		setMsgtype(MessageUtils.MASS_MESSAGE_TYPE_VOICE);
	}
	private MassVoice voice;
	public MassVoice getVoice() {
		return voice;
	}
	public void setVoice(MassVoice voice) {
		this.voice = voice;
	}
}
