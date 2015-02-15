package y1j2x34.wechat.message.resp;
/**
 * 响应消息——视频消息模型
 */
public class Video extends Media{
	private static final long serialVersionUID = -7855063101694134292L;
	private String Title;
	private String Description;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}