package y1j2x34.wechat.message.resp;

import java.io.Serializable;
/**
 * 响应消息——多媒体消息模型
 * @author 杨建新
 *
 */
public class Media implements Serializable{
	private static final long serialVersionUID = 1896223439444331187L;
	private String MediaId;
	/**
	 * 通过上传多媒体文件得到的id
	 * @return
	 */
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
