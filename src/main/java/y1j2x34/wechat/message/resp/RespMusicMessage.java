package y1j2x34.wechat.message.resp;

import y1j2x34.wechat.utils.MessageUtils;

/**
 * 回复音乐消息
 * <hr/>
 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E8%A2%AB%E5%8A%A8%E5%93%8D%E5%BA%94%E6%B6%88%E6%81%AF#.E5.9B.9E.E5.A4.8D.E9.9F.B3.E4.B9.90.E6.B6.88.E6.81.AF">发送被动响应消息#回复音乐消息</a>
 * <hr/>
 * @author yangjianxin
 * @see RespBaseMessage
 */
public class RespMusicMessage extends RespBaseMessage {

	private static final long serialVersionUID = -5152099396586526421L;
	private Music Music;
	public RespMusicMessage() {
		setMsgType(MessageUtils.RESP_MESSAGE_TYPE_MUSIC);
	}
	/**
	 * @return 语音
	 * @see Music
	 */
	public Music getMusic() {
		return Music;
	}
	/**
	 * @param music
	 * @see #getMusic()
	 */
	public void setMusic(Music music) {
		this.Music = music;
	}
}
