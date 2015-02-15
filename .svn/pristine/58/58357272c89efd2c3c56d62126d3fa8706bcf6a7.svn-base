package y1j2x34.wechat.message.mass;

import y1j2x34.wechat.utils.WeChatUtil;

/**
 * 群发消息-多媒体消息内容
 * @see MassMsgBody
 * @see MassVoice
 * @see MassImage
 * @see MassMPNews
 * @see MassMPVideo
 * @see #setMedia_id(String)
 * @author y1j2x34
 * @date 2014-7-3 上午9:46:01
 */
public abstract class MassMedia extends MassMsgBody{
	private static final long serialVersionUID = 1L;
	
	private String media_id;
	public MassMedia() {}
	
	public MassMedia(String media_id){
		this.media_id = media_id;
	}
	
	public String getMedia_id() {
		return media_id;
	}
	/**
	 * 媒体编号
	 * <p>
	 * 图文消息通过上传图文消息素材接口获得
	 * </p>
	 * <p>
	 * 多媒体消息通过上传多媒体文件接口获得
	 * </p>
	 * @see WeChatUtil#uploadNews(com.upower.weChat.pojo.UploadNews, com.upower.weChat.pojo.AccessToken)
	 * @see WeChatUtil#uploadMedia(y1j2x34.wechat.utils.WeChatUtil.UploadType, com.upower.weChat.pojo.AccessToken, java.io.InputStream)
	 */
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
}
