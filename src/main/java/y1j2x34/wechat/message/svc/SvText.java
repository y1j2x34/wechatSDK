package y1j2x34.wechat.message.svc;
/**
 * 客服消息——文本消息模型
 * @author 杨建新
 */
public class SvText {
	private String content;
	public SvText() {}
	public SvText(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
