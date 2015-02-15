package y1j2x34.wechat;

import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.message.resp.RespBaseMessage;
/**
 * 重试信息
 * @author y1j2x34
 */
public final class DuplicateInfo{
	private final ReqBaseMessage content;
	private final int times;
	private RespBaseMessage firstMsg;
	public DuplicateInfo(ReqBaseMessage content,int times) {
		this.content = content;
		this.times = times;
	}
	public ReqBaseMessage getContent() {
		return content;
	}
	public int getTimes() {
		return times;
	}
	void setRespMsg(RespBaseMessage msg){
		this.firstMsg = msg;
	}
	public RespBaseMessage getFirstMsg() {
		return firstMsg;
	}
}