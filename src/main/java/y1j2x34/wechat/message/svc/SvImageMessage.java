package y1j2x34.wechat.message.svc;
/**
 * 
 * 客服消息——图片消息
 *
 */
public class SvImageMessage extends SvBaseMessage{
	private static final long serialVersionUID = -8309803660155628230L;
	
	private SvMedia image;

	public SvMedia getImage() {
		return image;
	}

	public void setImage(SvMedia image) {
		this.image = image;
	}
}
