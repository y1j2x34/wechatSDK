package y1j2x34.wechat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.message.resp.RespBaseMessage;

public interface WeChatInterceptor {
	/**
	 * 拦截重复消息，最早调用
	 * @param info
	 * @param context
	 * @see RespBaseMessage#NONE
	 * @return RespBaseMessage#NONE或第一次处理的消息结果：{@link DuplicateInfo#getFirstMsg()}或自定义消息来拦截请求，或者还可以返回空消息不拦截
	 */
	RespBaseMessage duplicateHandle(DuplicateInfo info,Context context);
	/**
	 * 接收到用户消息请求时在业务处理前，duplicateHandle方法后调用
	 * @param reqMessage
	 * @param request TODO
	 * @param response TODO
	 * @param context TODO
	 * @return true 表示继续处理业务，false 拦截过滤掉该请求！
	 */
	boolean preHandle(ReqBaseMessage reqMessage,HttpServletRequest request, HttpServletResponse response, Context context);
	/**
	 * 在业务处理器处理完后调用
	 * @param reqMessage
	 * @param respMessage
	 * @param response TODO
	 * @param context TODO
	 */
	void postHandle(ReqBaseMessage reqMessage,RespBaseMessage respMessage,HttpServletRequest request, HttpServletResponse response, Context context);
	/**
	 * 在业务完全处理完并且返回用户消息后调用
	 * @param ex 
	 * @param data
	 */
	void afterComplection(Context context,Exception ex);
}
