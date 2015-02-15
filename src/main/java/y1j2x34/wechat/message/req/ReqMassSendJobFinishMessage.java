package y1j2x34.wechat.message.req;
/**
 * 事件推送-群发结果
 * @author y1j2x34
 *
 */
public class ReqMassSendJobFinishMessage extends ReqEventMessage{

	private static final long serialVersionUID = 1L;
	private String MsgID;
	/**
	 * <pre>
	 * 群发的结构，为“send success”或“send fail”或“err(num)”。
	 * 但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。
	 * err(num)是审核失败的具体原因，可能的情况如下：
	 * err(10001), //涉嫌广告 		err(20001), //涉嫌政治
	 * err(20004), //涉嫌社会		err(20002), //涉嫌色情
	 * err(20006), //涉嫌违法犯罪 	err(20008),//涉嫌欺诈
	 * err(20013), //涉嫌版权 		err(22000),//涉嫌互推(互相宣传)
	 * err(21000), //涉嫌其他
	 * </pre>
	 */
	private String status;
	/**
	 * group_id下粉丝数；或者openid_list中的粉丝数
	 */
	private String TotalCount;
	/**
	 * <pre>
	 * 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的
	 * 过滤）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount
	 * </pre>
	 */
	private String filterCount;
	/**
	 * 发送成功的粉丝数
	 */
	private String sentCount;
	/**
	 * 发送失败的粉丝数
	 */
	private String ErrorCount;
	
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
	public String getMsgID() {
		return MsgID;
	}
	public void setMsgID(String msgID) {
		MsgID = msgID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(String totalCount) {
		TotalCount = totalCount;
	}
	public String getFilterCount() {
		return filterCount;
	}
	public void setFilterCount(String filterCount) {
		this.filterCount = filterCount;
	}
	public String getSentCount() {
		return sentCount;
	}
	public void setSentCount(String sentCount) {
		this.sentCount = sentCount;
	}
	public String getErrorCount() {
		return ErrorCount;
	}
	public void setErrorCount(String errorCount) {
		ErrorCount = errorCount;
	}
}
