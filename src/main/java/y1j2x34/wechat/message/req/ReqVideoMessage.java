package y1j2x34.wechat.message.req;
/**
 * 请求视频消息
 * <hr/>
 * 
 */
public class ReqVideoMessage extends ReqMediaMessage{

	private static final long serialVersionUID = -1299707459003760669L;
	
	private String ThumbMediaId;
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 * @return
	 */
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
