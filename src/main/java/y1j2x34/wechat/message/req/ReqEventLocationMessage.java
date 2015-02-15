package y1j2x34.wechat.message.req;

/**
 * 获取用户地理位置
 * <hr/>
 * <ol>
 * 获取用户地理位置的方式有两种:
 * <li>仅在进入会话时上报一次，</li>
 *<li> 进入会话后每隔5秒上报一次。</li>
 * </ol>
 * 公众号可以在公众平台网站中设置
 * <br/>
 * <font color="#FF00FF">
 * 区别于 {@linkplain #ReqLocationMessage},<br/>
 * 当前类是公众号拥有获取用户地理位置消息的权限后，软件自动发送
 * </font>
 * @author 杨建新
 */
public class ReqEventLocationMessage extends ReqEventMessage{

	private static final long serialVersionUID = 6209099859245913691L;
	
	
	private double Latitude;
	
	private double Longitude;
	
	private double Precision;
	
	private String EventKey;
	/**
	 * @return
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 * @see #getEvent()
	 * @see #setEventKey(String)
	 */
	public String getEventKey() {
		return EventKey;
	}
	/**
	 * @param eventKey 事件KEY值
	 * @see #getEventKey()
	 */
	public void setEventKey(String eventKey) {
		this.EventKey = eventKey;
	}
	/**
	 * @return 事件类型 LOCATION
	 * @see #setEvent(String)
	 */
	public String getEvent() {
		return super.getEvent();
	}
	/**
	 * @return 地理位置纬度
	 * @see #setLatitude(double)
	 */
	public double getLatitude() {
		return Latitude;
	}
	/**
	 * @param latitude
	 * @see #getLatitude()
	 */
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	/**
	 * @return  地理位置精度
	 */
	public double getLongitude() {
		return Longitude;
	}
	/**
	 * @param longitude
	 * @see #getLongitude()
	 */
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	/**
	 * @return 地理位置精度
	 */
	public double getPrecision() {
		return Precision;
	}
	/**
	 * @param precision
	 * @see #setPrecision(double)
	 */
	public void setPrecision(double precision) {
		Precision = precision;
	}
}
