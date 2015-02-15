package y1j2x34.wechat.message.req;

import y1j2x34.wechat.utils.MessageUtils;

/**
 * 地理位置消息
 * <hr/>
 * @author 杨建新
 * @see ReqBaseMessage
 */
public class ReqLocationMessage extends ReqBaseMessage{

	private static final long serialVersionUID = -926278456809286472L;
	
	private String Location_X;
	
	private String Location_Y;
	
	private String Scale;
	
	private String Label;
	public ReqLocationMessage() {
		setMsgType(MessageUtils.REQ_MESSAGE_TYPE_LOCATION);
	}
	/**
	 * @return 地理位置维度
	 */
	public String getLocation_X() {
		return Location_X;
	}
	/**
	 * @param location_X
	 * @see #getLocation_X()
	 */
	public void setLocation_X(String location_X) {
		this.Location_X = location_X;
	}
	/**
	 * @return 地理位置经度
	 * @see #setLoaction_Y(String)
	 */
	public String getLocation_Y() {
		return Location_Y;
	}
	/**
	 * @param loaction_Y
	 * @see #getLoaction_Y()
	 */
	public void setLocation_Y(String location_Y) {
		this.Location_Y = location_Y;
	}
	/**
	 * @return 地图缩放大小
	 * @see #setScale(String)
	 */
	public String getScale() {
		return Scale;
	}
	/**
	 * @param scale
	 * @see #getScale()
	 */
	public void setScale(String scale) {
		this.Scale = scale;
	}
	/**
	 * @return 地理位置信息
	 * @see #setLabel(String)
	 */
	public String getLabel() {
		return Label;
	}
	/**
	 * @param label
	 * @see #getLabel()
	 */
	public void setLabel(String label) {
		this.Label = label;
	}
}
