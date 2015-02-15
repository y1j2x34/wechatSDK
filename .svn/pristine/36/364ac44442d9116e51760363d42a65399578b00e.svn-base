package y1j2x34.wechat.message.svc;

import y1j2x34.wechat.utils.WeChatUtil;
/**
 * 客服消息——音乐消息模型
 * @author 杨建新
 *
 */
public class SvMusic extends Svtd{
	private static final long serialVersionUID = -5741826353549748075L;
	
	private String musicurl;
	private String hqmusicurl;
	private String thumb_media_id;
	/**
	 * 音乐链接
	 * @return
	 */
	public String getMusicurl() {
		return musicurl;
	}
	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}
	/**
	 * 高品质音乐链接，wifi环境优先使用该链接播放音乐
	 * @return
	 */
	public String getHqmusicurl() {
		return hqmusicurl;
	}
	public void setHqmusicurl(String hqmusicurl) {
		this.hqmusicurl = hqmusicurl;
	}
	/**
	 * 缩略图的媒体ID，使用多媒体下载接口下载
	 * @see {@link WeChatUtil#getMediaResponse(String)}
	 * @return
	 */
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
}
