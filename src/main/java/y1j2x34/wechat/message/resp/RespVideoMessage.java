package y1j2x34.wechat.message.resp;
/**
 * 响应消息——视频消息
 * @author 杨建新
 *
 */
public class RespVideoMessage extends RespBaseMessage{

	private static final long serialVersionUID = -6169501554093601936L;
	private Video Video;
	
	public Video getVideo() {
		return Video;
	}
	public void setVideo(Video video) {
		Video = video;
	}
}