package y1j2x34.wechat.message.req;

/**
 * 
 * 语音识别结果
 * <br/>
 * 由于客户端缓存，开发者开启或者关闭语音识别功能，对新关注者立刻生效，对已关注用户需要24小时生效。开发者可以重新关注此帐号进行测试
 * <hr/>
 * @author 杨建新
 * @date 2014-1-20
 */
public class ReqSpeechRecognitionMessage extends ReqMediaMessage{

	private static final long serialVersionUID = -7619718738298576226L;
	private String Recognition;
	
	private String Format;
	/**
	 * 语音识别结果，UTF-8
	 * @return
	 */
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	/**
	 * 微信5.x 后加上去的，表示语音格式：amr
	 * @return
	 */
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
}
