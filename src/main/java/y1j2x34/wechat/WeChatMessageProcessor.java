package y1j2x34.wechat;


import y1j2x34.wechat.expt.UnknownMsgTypeException;
import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.message.resp.RespBaseMessage;

public interface WeChatMessageProcessor {
	RespBaseMessage execute(ReqBaseMessage reqMsg,Context context)throws UnknownMsgTypeException;
}