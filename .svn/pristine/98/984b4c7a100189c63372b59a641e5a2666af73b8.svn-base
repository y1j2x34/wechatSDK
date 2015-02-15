package y1j2x34.wechat;

import y1j2x34.wechat.utils.WeChatUtil;

/**
 * 微信接口调用日志输出
 * @see WeChatUtil#logger
 * @see WeChatUtil#keepSilence(boolean) 日志开关
 * @author yangjianxin
 * @date 2014-12-10 下午4:38:00
 */
public interface WeChatAPILogger {
	/**
	 * @param api 调用API
	 * @param req 请求数据
	 * @param t	调用异常
	 */
	void log(API api,Object req,Throwable t);
	/**
	 * @param api
	 * @param req
	 * @param resp 响应数据
	 */
	void log(API api,Object req,Object resp);
	/**
	 * @param api 
	 * @param req 请求数据
	 */
	void log(API api,Object req);
}
