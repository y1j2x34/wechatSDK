package y1j2x34.wechat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.message.resp.RespBaseMessage;

public abstract class WeChatInterceptorAdapter implements WeChatInterceptor {

	@Override
	public boolean preHandle(ReqBaseMessage reqMessage,
			HttpServletRequest request, HttpServletResponse response,
			Context context) {
		return true;
	}

	@Override
	public void postHandle(ReqBaseMessage reqMessage,
			RespBaseMessage respMessage, HttpServletRequest request,
			HttpServletResponse response, Context context) {
	}

	@Override
	public RespBaseMessage duplicateHandle(DuplicateInfo info,
			Context context) {
		return null;
	}

	@Override
	public void afterComplection(Context context, Exception ex) {
	}

}
