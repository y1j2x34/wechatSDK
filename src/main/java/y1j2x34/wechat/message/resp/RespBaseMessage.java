package y1j2x34.wechat.message.resp;

import y1j2x34.wechat.message.BaseMessage;
import y1j2x34.wechat.message.req.ReqTextMessage;
/**
 * 响应消息基类（公众账号 -> 用户）
 * <hr/>
 * @author 杨建新
 * @date 2013-12-25
 * @version 0.01
 * @see ReqTextMessage
 * @see RespNewsMessage
 * @see RespMusicMessage
 */
public class RespBaseMessage extends BaseMessage{
	public static final RespBaseMessage NONE = new RespBaseMessage();
	private static final long serialVersionUID = -5706680559160647065L;
	
	private String ToUserName;
	private String FromUserName;
	
//	private int FuncFlag;
	
	public RespBaseMessage() {}
	
	RespBaseMessage(String toUserName,String fromUserName){
		setToUserName(toUserName);
		setFromUserName(fromUserName);
	}
	/**
	 * @return 接收方账号（收到的OpenID）
	 * @see #setToUserName(String)
	 */
	public String getToUserName() {
		return ToUserName;
	}
	/**
	 * @param toUserName
	 * @see #getToUserName()
	 */
	public void setToUserName(String toUserName) {
		this.ToUserName = toUserName;
	}
	/**
	 * @return 开发者微信号
	 * @see #setFromUserName(String)
	 */
	public String getFromUserName() {
		return FromUserName;
	}
	/**
	 * @param fromUserName
	 * @see #getFromUserName()
	 */
	public void setFromUserName(String fromUserName) {
		this.FromUserName = fromUserName;
	}
//	/**
//	 * @return 位0x0001被标志时，星标刚收到的消息
//	 * @see #setFuncFlag(int)
//	 */
//	public int getFuncFlag() {
//		return FuncFlag;
//	}
//	/**
//	 * @param funcFlag
//	 * @see #getFuncFlag()
//	 */
//	public void setFuncFlag(int funcFlag) {
//		this.FuncFlag = funcFlag;
//	}
}
