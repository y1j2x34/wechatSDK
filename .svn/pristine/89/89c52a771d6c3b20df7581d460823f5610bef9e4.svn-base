package y1j2x34.wechat;

import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.message.resp.RespBaseMessage;
/**
 * 消息处理监听器
 * @author y1j2x34
 *
 */
public interface WeChatListener {
	/**
	 * 消息处理前
	 * @param message
	 * @param params
	 * @return
	 */
	Object beforeProcessMessage(ReqBaseMessage message,Object params);
	/**
	 * 处理重试消息
	 * @param message 请求消息
	 * @param params
	 * @param count 重试次数
	 * @deprecated 改用拦截器
	 */
	void duplicateMessage(ReqBaseMessage message,Object params, int count);
	/**
	 * 消息处理后
	 * @param resp 响应给用户的消息
	 * @param message 接收到用户的消息
	 * @param params 
	 * @param bdata {@link #beforeProcessMessage(ReqBaseMessage, Object)}返回的数据
	 */
	void afterProcessedMessage(RespBaseMessage resp,ReqBaseMessage message,Object params,Object bdata);
}
