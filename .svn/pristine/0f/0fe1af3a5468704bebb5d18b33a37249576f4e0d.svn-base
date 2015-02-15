package y1j2x34.wechat;

import java.io.Serializable;

import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.message.resp.RespBaseMessage;
/**
 * 微信端消息监听器
 * @author y1j2x34
 */
public interface WeChatMessageListener<Msg extends ReqBaseMessage> extends Serializable{
	public RespBaseMessage onReceive(Msg message,Context context);
}
