package y1j2x34.wechat.expt;

import y1j2x34.wechat.Context;
import y1j2x34.wechat.DuplicateInfo;
import y1j2x34.wechat.WechatCoreService;
import y1j2x34.wechat.message.resp.RespTextMessage;
import y1j2x34.wechat.utils.MessageUtils;

/**
 * 微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次。
 * <br/>该异常在判断为重试消息时抛出
 * @see WechatCoreService#doMsg(javax.servlet.http.HttpServletRequest, Context)
 * @author 杨建新
 */
public class DuplicateMessageException extends Exception{

	private static final long serialVersionUID = 4090576645677142487L;
	
	private DuplicateInfo info;
	
	public DuplicateMessageException(DuplicateInfo info) {
		super();
		this.info = info;
	}
	/**
	 * 获得重试信息
	 * @return
	 */
	public DuplicateInfo getDuplicateInfo(){
		return info;
	}
	/**
	 * 默认（重试消息的响应内容）
	 * @return
	 */
	public String getDefaultResponseMessage(){
		RespTextMessage resp = MessageUtils.buildRespMsg(info.getContent(), new RespTextMessage());
		resp.setMsgType(MessageUtils.RESP_MESSAGE_TYPE_TEXT);
		resp.setContent("您的操作过于频繁！");
		return MessageUtils.respMessageToXml(resp);
	}
}
