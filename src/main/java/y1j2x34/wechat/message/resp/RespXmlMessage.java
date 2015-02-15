package y1j2x34.wechat.message.resp;
/**
 * 为需要直接返回xml格式的响应消息提供支持
 */
public class RespXmlMessage extends RespBaseMessage{

	private static final long serialVersionUID = 1L;
	private final String xml;
	public RespXmlMessage(String xml) {
		this.xml = xml;
	}
	public String getXml() {
		return xml;
	}
}
