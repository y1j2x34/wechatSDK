package y1j2x34.wechat.pojo;

import java.io.Serializable;

/**
 * 上传消息返回数据
 * @author y1j2x34
 * 
 */
public class UploadResult implements Serializable{
	private static final long serialVersionUID = 1L;
	//媒体文件类型：图片（image）、语音（voice）、视频（video）、缩略图（thumb）、图文消息（news）
	private String type;
	//媒体文件/图文消息上传后获取的唯一标识
	private String media_id;
	//媒体上传时间
	private int created_at;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public int getCreated_at() {
		return created_at;
	}
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
}
