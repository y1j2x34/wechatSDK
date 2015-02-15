package y1j2x34.wechat.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import y1j2x34.utils.ReflectUtils;
import y1j2x34.wechat.expt.UnknownMsgTypeException;
import y1j2x34.wechat.message.BaseMessage;
import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.message.req.ReqDBarCodeMessage;
import y1j2x34.wechat.message.req.ReqEncryptMessage;
import y1j2x34.wechat.message.req.ReqEventLocationMessage;
import y1j2x34.wechat.message.req.ReqEventMessage;
import y1j2x34.wechat.message.req.ReqImageMessage;
import y1j2x34.wechat.message.req.ReqLinkMessage;
import y1j2x34.wechat.message.req.ReqLocationMessage;
import y1j2x34.wechat.message.req.ReqMassSendJobFinishMessage;
import y1j2x34.wechat.message.req.ReqMenuClickMessage;
import y1j2x34.wechat.message.req.ReqSpeechRecognitionMessage;
import y1j2x34.wechat.message.req.ReqSubscribeMessage;
import y1j2x34.wechat.message.req.ReqTextMessage;
import y1j2x34.wechat.message.req.ReqVideoMessage;
import y1j2x34.wechat.message.req.ReqVoiceMessage;
import y1j2x34.wechat.message.resp.Article;
import y1j2x34.wechat.message.resp.Image;
import y1j2x34.wechat.message.resp.Music;
import y1j2x34.wechat.message.resp.RespBaseMessage;
import y1j2x34.wechat.message.resp.RespImageMessage;
import y1j2x34.wechat.message.resp.RespMusicMessage;
import y1j2x34.wechat.message.resp.RespNewsMessage;
import y1j2x34.wechat.message.resp.RespTransferCustomerServiceMessage;
import y1j2x34.wechat.message.resp.RespVideoMessage;
import y1j2x34.wechat.message.resp.RespVoiceMessage;
import y1j2x34.wechat.message.resp.Video;
import y1j2x34.wechat.message.resp.Voice;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
/**
 * 处理所有消息转换以及判断消息类型的工具
 * @author yangjianxin
 */
public class MessageUtils {
	/** 
     * 返回消息类型：文本 
     */  
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";  
  
    /** 
     * 返回消息类型：图文 
     * @see RespNewsMessage
     */  
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    /**
     * 返回消息类型：图片
     * @see RespImageMessage
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image"; 
    /**
     * 返回消息类型：语音
     * @see RespVoiceMessage
     */
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice"; 
    /**
     * 返回消息类型：视频
     * @see RespVideoMessage
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video"; 
    /**
     * 回复音乐类型消息
     * @see RespMusicMessage
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    /**
     * 返回该类型消息，微信会将用户消息转发至多客服系统
     * @see RespTransferCustomerServiceMessage
     */
    public static final String RESP_MESSAGE_TRANSFER_CUSTOMER_SERVICE="transfer_customer_service";
    /** 
     * 请求消息类型：文本 
     * @See RespTextMessage
     */  
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
  
    /** 
     * 请求消息类型：图片
     * @see ReqImageMessage
     */  
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";  
 
    /**
     * 请求消息类型：视频
     * @see ReqVideoMessage
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    
    /** 
     * 请求消息类型：链接 
     * @see ReqLinkMessage
     */  
    public static final String REQ_MESSAGE_TYPE_LINK = "link";  
  
    /** 
     * 请求消息类型：地理位置 
     * @see ReqLocationMessage
     */  
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    /**
     * 事件类型：上报地理位置
     * @see ReqEventLocationMessage
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
  
    /** 
     * 请求消息类型：音频 
     * @see ReqVoiceMessage
     */  
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";  
  
    /** 
     * 请求消息类型：推送 
     * @see ReqEventMessage
     */  
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";  
  
    /** 
     * 事件类型：subscribe(订阅) 
     * @see ReqSubscribeMessage
     */  
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
  
    /** 
     * 事件类型：unsubscribe(取消订阅) 
     * @see ReqSubscribeMessage
     */  
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
    /**
     * 事件类型：SCAN(用户已关注时的扫描带场景值得二维码事件)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";
    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     * @see ReqMenuClickMessage
     */  
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 事件类型：VIEW（链接菜单点击事件）
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";
    /**
     * 事件类型：MASSSENDJOBFINISH（群发消息结果事件推送）
     */
    public static final String EVENT_TYPE_MASSSENDJOBFINISH = "MASSSENDJOBFINISH";
    /**
     * 客服消息类型：文本
     */
    public static final String SV_MESSAGE_TYPE_TEXT = "text";
    /**
     * 客服消息类型：图片
     */
    public static final String SV_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 客服消息类型：语音
     */
    public static final String SV_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 客服消息类型：视频
     */
    public static final String SV_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 客服消息类型：音乐
     */
    public static final String SV_MESSAGE_TYPE_MUSIC= "music";
    /**
     * 客服消息类型：图文
     */
    public static final String SV_MESSAGE_TYPE_NEWS= "news";
    /**
     * 群发消息类型：图文消息
     */
    public static final String MASS_MESSAGE_TYPE_NEWS = "mpnews";
    /**
     * 群发消息类型：文本消息
     */
    public static final String MASS_MESSAGE_TYPE_TEXT = "text";
    /**
     * 群发消息类型：语音消息
     */
    public static final String MASS_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 群发消息类型：图片消息
     */
    public static final String MASS_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 群发消息类型：视频消息
     */
    public static final String MASS_MESSAGE_TYPE_VIDEO = "mpvideo";
    /**
     * 用户请求消息类型
     */
    public static final int ACTION_TYPE_REQ = 1;
    /**
     * 公众号响应消息类型
     */
	public static final int ACTION_TYPE_RESP = 2;
	/**
	 * 客服消息类型
	 */
	public static final int ACTION_TYPE_SERVICE = 3;
	/**
	 * 群发消息类型
	 */
	public static final int ACTION_TYPE_MASS = 4;
	/**
	 * 微信消息创建时间单位，默认为秒
	 */
	private static TimeUnit currentTimeUnit = TimeUnit.SECONDS;
	
	private static class Holder{
		private static final XStream XSTREAM = new XStream(new XppDriver(){
			public com.thoughtworks.xstream.io.HierarchicalStreamWriter createWriter(java.io.Writer out) {
				return new PrettyPrintWriter(out){
					boolean cdata = true;
					@Override
					protected void writeText(QuickWriter writer, String text) {
						if(cdata){
							writer.write("<![CDATA["+text+"]]>");
						}else{
							writer.write(text);
						}
					}
				};
			};
		});
	}
	/**
	 * 将微信服务器发送过来的xml数据转换成Map
	 * @param xmlStream 数据流，使用 {@link HttpServletRequest#getInputStream()}
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> parseToMap(InputStream xmlStream)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		SAXReader sr = new SAXReader();
		try {
			Document doc = sr.read(xmlStream);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> elms = root.elements();
			for(Element elm:elms){
				map.put(elm.getName(), elm.getTextTrim());
			}
		} catch (DocumentException e) {
			throw new Exception(e);
		}
		return map;
	}
	/**
	 * 将请求的Map转换成对应消息类型的请求消息对象
	 * @see ReqBaseMessage
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static ReqBaseMessage createFromReqMap(Map<String,String> requestMap)throws Exception{
//		StateMachine<WeChatClassifyAction> machine = ClassifyStateMachineFactory.getStateMachine();
//		machine.change(requestMap);
//		return (ReqBaseMessage)machine.current().getAction().execute(requestMap, null);
		if(requestMap.size()  == 2 && requestMap.containsKey("Encrypt")){
			return copyToReqMsg(new ReqEncryptMessage(), requestMap);
		}
		// 吓死人了 -_-!!
		String msgType = requestMap.get("MsgType");
		if(msgType.equals(REQ_MESSAGE_TYPE_TEXT)){
			return copyToReqMsg(new ReqTextMessage(), requestMap);
		}else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_EVENT)) {
			String eventType = requestMap.get("Event");
			if(eventType.equals(MessageUtils.EVENT_TYPE_LOCATION)){
				return copyToReqMsg(new ReqEventLocationMessage(), requestMap);
			}else if(eventType.equals(EVENT_TYPE_SUBSCRIBE)){
				if(requestMap.containsKey("EventKey")){
					String eventKey = requestMap.get("EventKey");
					if(eventKey.startsWith("qrscene_")){
						return copyToReqMsg(new ReqDBarCodeMessage(), requestMap);
					}else{
						return copyToReqMsg(new ReqSubscribeMessage(), requestMap);
					}
				}else{
					return copyToReqMsg(new ReqSubscribeMessage(), requestMap);
				}
			}else if(eventType.equals(EVENT_TYPE_CLICK) || eventType.equals(EVENT_TYPE_VIEW)){
				return copyToReqMsg(new ReqMenuClickMessage(), requestMap);
			}else if(eventType.equals(EVENT_TYPE_SCAN)){
				return copyToReqMsg(new ReqDBarCodeMessage(), requestMap);
			}else if(eventType.equals(EVENT_TYPE_UNSUBSCRIBE)){
				return copyToReqMsg(new ReqSubscribeMessage(), requestMap);
			}else if(eventType.equals(EVENT_TYPE_MASSSENDJOBFINISH)){
				return copyToReqMsg(new ReqMassSendJobFinishMessage(), requestMap);
			}else{
				return copyToReqMsg(new ReqEventMessage(), requestMap);
			}
		}else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_IMAGE)) {
			return copyToReqMsg(new ReqImageMessage(), requestMap);
		}else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_LOCATION)) {
			return copyToReqMsg(new ReqLocationMessage(), requestMap);
		}else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_LINK)) {
			return copyToReqMsg(new ReqLinkMessage(), requestMap);
		}else if (msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_VOICE)) {
			if(requestMap.get("Recognition") != null){
				return copyToReqMsg(new ReqSpeechRecognitionMessage(), requestMap);
			}else{
				return copyToReqMsg(new ReqVoiceMessage(), requestMap);
			}
		}else if(msgType.equals(MessageUtils.REQ_MESSAGE_TYPE_VIDEO)){
			return copyToReqMsg(new ReqVideoMessage(), requestMap);
		}else{
			throw new UnknownMsgTypeException(msgType);
		}
	}
	/**
	 * 转换到指定的消息对象中
	 * @param dst
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static <Message extends ReqBaseMessage> Message copyToReqMsg(Message dst,Map<String,String> src)throws Exception{
		Class<? extends ReqBaseMessage> dstType = dst.getClass();
		for(Entry<String,String> entry:new HashSet<Entry<String,String>>(src.entrySet())){
			String key = entry.getKey();
			String value = src.remove(key);
			try{
				Field field = ReflectUtils.getField(key, dstType);
				Object cvt =ReflectUtils.convert(value, field.getType());
				if(cvt != null){
//					Method setMethod = ReflectUtils.getMethod("set"+key, dstType,field.getType());
//					setMethod.invoke(dst, cvt);
					field.setAccessible(true);
					field.set(dst, cvt);
				}
			}catch(Throwable e){}
		}
		return dst;
	}
	
	private static final Map<Class<?>,Map<String,Class<?>>> alias_map = new HashMap<Class<?>,Map<String,Class<?>>>();
	static{
		Map<String,Class<?>> news_alias = new HashMap<String,Class<?>>();
		news_alias.put("item", Article.class);
		Map<String,Class<?>> music_alias = new HashMap<String,Class<?>>();
		music_alias.put("item", Music.class);
		
		Map<String,Class<?>> image_alias = new HashMap<String,Class<?>>();
		image_alias.put("item", Image.class);
		Map<String,Class<?>> video_alias = new HashMap<String,Class<?>>();
		video_alias.put("item", Video.class);
		Map<String,Class<?>> voice_alias = new HashMap<String,Class<?>>();
		voice_alias.put("item", Voice.class);
		alias_map.put(RespNewsMessage.class, news_alias);
		alias_map.put(RespMusicMessage.class, music_alias);
		alias_map.put(RespImageMessage.class, image_alias);
		alias_map.put(RespVideoMessage.class, video_alias);
		alias_map.put(RespVoiceMessage.class, voice_alias);
	}
	/**
	 * 将回复消息对象转换成xml的字符串，用于返回给微信服务器
	 * @param msg
	 * @return
	 */
	public static String respMessageToXml(RespBaseMessage msg){
		Map<String,Class<?>> alias = alias_map.get(msg.getClass());
		if(alias!=null){
			for(Entry<String,Class<?>> entry:alias.entrySet()){
				Holder.XSTREAM.alias(entry.getKey(), entry.getValue());
			}
		}
		return messageToXml(msg);
	}
	
	public static String messageToXml(BaseMessage msg){
		Holder.XSTREAM.alias("xml", msg.getClass());
		return Holder.XSTREAM.toXML(msg);
	}
	/**
	 * 获得字符串对应的编码的字节长度
	 * @param encoding
	 * @param content
	 * @return
	 */
	public static int getByteSize(String encoding,String content){
		int size = 0;
		if(content != null){
			try {
				return content.getBytes(encoding).length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
	/**
	 * 格式化时间
	 * @param dateTime 该日期单位按微信接口中的时间单位
	 * @see #currentCreateTime()
	 * @return
	 */
	public static String formatTime(long dateTime){
		return new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(
				new Date(currentTimeUnit.convert(dateTime, TimeUnit.MILLISECONDS))
				);
	}
	/**
	 * 设置了 createTime、fromUserName、toUserName
	 * @param reqMsg
	 * @param respMsg
	 * @return
	 */
	public static <T extends RespBaseMessage> T buildRespMsg(ReqBaseMessage reqMsg,T respMsg){
		respMsg.setCreateTime((long) currentCreateTime());
		respMsg.setFromUserName(reqMsg.getToUserName());
		respMsg.setToUserName(reqMsg.getFromUserName());
//		respMsg.setFuncFlag(0);
		return respMsg;
	}
	/**
	 * emoji表情
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji){
		return String.valueOf(Character.toChars(hexEmoji));
	}
	/**
	 * 微信消息的创建时间,依照微信接口的单位标准
	 * @return 当前时间，单位：{@link #currentTimeUnit}
	 */
	public static final int currentCreateTime(){
		return (int)currentTimeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	//========== 判断微信服务器传过来的消息的类型
	public static final boolean isTextMessage(ReqBaseMessage reqMsg){
		return reqMsg instanceof ReqTextMessage && REQ_MESSAGE_TYPE_TEXT.equals(reqMsg.getMsgType());
	}
	public static final boolean isLocationMessage(ReqBaseMessage reqMsg){
		return reqMsg instanceof ReqLocationMessage && REQ_MESSAGE_TYPE_LOCATION.equals(reqMsg.getMsgType());
	}
	public static final boolean isImageMessage(ReqBaseMessage reqMsg){
		return reqMsg instanceof ReqImageMessage && REQ_MESSAGE_TYPE_IMAGE.equals(reqMsg.getMsgType());
	}
	public static final boolean isVoiceMessage(ReqBaseMessage reqMsg){
		return reqMsg instanceof ReqVoiceMessage && REQ_MESSAGE_TYPE_VOICE.equals(reqMsg.getMsgType());
	}
	public static final boolean isVideoMessage(ReqBaseMessage reqMsg){
		return reqMsg instanceof ReqVideoMessage && REQ_MESSAGE_TYPE_VIDEO.equals(reqMsg.getMsgType());
	}
	public static final boolean isLinkMessage(ReqBaseMessage reqMsg){
		return reqMsg instanceof ReqLinkMessage && REQ_MESSAGE_TYPE_LINK.equals(reqMsg.getMsgType());
	}
	public static final boolean isEventMessage(ReqBaseMessage reqMsg){
		return reqMsg instanceof ReqEventMessage && REQ_MESSAGE_TYPE_EVENT.equals(reqMsg.getMsgType());
	}
	
	public static final boolean isMenuClickMessage(ReqBaseMessage reqMsg){
		return isEventMessage(reqMsg) && EVENT_TYPE_CLICK.equals(((ReqEventMessage)reqMsg).getEvent());
	}
	/**
	 * 点击菜单跳转链接时的事件推送
	 * @param reqMsg
	 * @return
	 */
	public static final boolean isViewMenuClickMessage(ReqBaseMessage reqMsg){
		return isEventMessage(reqMsg) && EVENT_TYPE_VIEW.equals(((ReqEventMessage)reqMsg).getEvent());
	}
	
	public static final boolean isSubscribeMessage(ReqBaseMessage reqMsg){
		return isEventMessage(reqMsg) && EVENT_TYPE_SUBSCRIBE.equals(((ReqEventMessage)reqMsg).getEvent());
	}
	public static final boolean isUnSubscribeMessage(ReqBaseMessage reqMsg){
		return isEventMessage(reqMsg) && EVENT_TYPE_UNSUBSCRIBE.equals(((ReqEventMessage)reqMsg).getEvent());
	}
	public static final boolean isEventLocationMessage(ReqBaseMessage reqMsg){
		return isEventMessage(reqMsg) && EVENT_TYPE_LOCATION.equals(((ReqEventMessage)reqMsg).getEvent());
	}
	public static final boolean isSpeechRecognizeMessage(ReqBaseMessage reqMsg){
		return reqMsg instanceof ReqSpeechRecognitionMessage && REQ_MESSAGE_TYPE_VOICE.equals(reqMsg.getMsgType());
	}
	public static final boolean isSubscribed_DBarCodeMessage(ReqBaseMessage message){
		return message instanceof ReqDBarCodeMessage && EVENT_TYPE_SCAN.equals(((ReqDBarCodeMessage)message).getEvent());
	}
	public static final boolean isUnSubscribe_DBarCodeMessage(ReqBaseMessage message){
		return message instanceof ReqDBarCodeMessage && EVENT_TYPE_SUBSCRIBE.equals(((ReqDBarCodeMessage)message).getMsgType()) && ((ReqDBarCodeMessage)message).getEventKey().startsWith("qrscene_");
	}
	/**
	 * 设置微信消息创建时间单位，
	 * @param currentTimeUnit
	 */
	public static void setCurrentTimeUnit(TimeUnit currentTimeUnit) {
		if(currentTimeUnit == null) throw new IllegalArgumentException("Time unit cannot be null!");
		MessageUtils.currentTimeUnit = currentTimeUnit;
	}
}
