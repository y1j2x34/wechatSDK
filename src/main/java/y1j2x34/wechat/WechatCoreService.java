package y1j2x34.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import y1j2x34.utils.ReflectUtils;
import y1j2x34.wechat.expt.DuplicateMessageException;
import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.message.resp.RespBaseMessage;
import y1j2x34.wechat.message.resp.RespXmlMessage;
import y1j2x34.wechat.utils.MessageUtils;
import y1j2x34.wechat.utils.SignUtils;
/**
 * 如果在Spring中配置该Service，需要 设置 init-method=onCreate和destroy-method = onDestroy
 * @author y1j2x34
 * @date 2014-11-18 下午2:48:46
 */
public class WechatCoreService{
	//auth2.0校验
	private SignUtils sign = null;
	private WeChatMessageProcessor processor;
	private int duplicateMessageInterval = 5;
	private boolean debug;
	private static final Logger LOG = Logger.getLogger(WechatCoreService.class);
	
	private volatile ExecutorService exes;
	private volatile Map<String,DuplicateInfo> duplicateMap ;
	
	private List<WeChatInterceptor> interceptors = new LinkedList<WeChatInterceptor>();
	private List<WeChatListener> listeners = new LinkedList<WeChatListener>();
	/**
	 * 初始化服务，必须先调用
	 */
	public void onCreate() {
		sign = SignUtils.getInstance();
		exes = Executors.newSingleThreadExecutor();
		duplicateMap = Collections.synchronizedMap(new HashMap<String,DuplicateInfo>());
		exes.execute(new Runnable() {
			public void run() {
				while(true){
					long start = System.currentTimeMillis();
					//刷新重复消息
					for(Entry<String, DuplicateInfo> entry:new HashSet<Entry<String, DuplicateInfo>>(duplicateMap.entrySet())){
						DuplicateInfo info = entry.getValue();
						if(info.getTimes() > 2){
							duplicateMap.remove(entry.getKey());
						}
					}
					long end = System.currentTimeMillis();
					try {
						Thread.sleep(5000-(end-start));
					} catch (InterruptedException e) {}
				}
			}
		});
	}
	/**
	 * 销毁服务
	 */
	public void onDestroy(){
		exes.shutdownNow();
		duplicateMap.clear();
		listeners.clear();
		interceptors.clear();
		exes = null;
		if(debug)
			LOG.info("微信服务销毁！");
	}
	/**
	 * 微信接口接入
	 * 
	 * @param requestMap
	 * @return
	 */
	public String access(String token,HttpServletRequest request) {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		if(signature == null || timestamp==null || nonce == null || echostr == null){
			return null;
		}
		
		if (sign.checkSignature(token,signature, timestamp, nonce)) {
			return echostr;
		}
		return null;
	}
	
	/**
	 * 在servlet中，需要在post请求方法中调用
	 * @deprecated
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String doMsg(HttpServletRequest request,Context context) throws DuplicateMessageException,Exception{
//		AppContext.getInstance().data(
//				AppContext.DATA_BASE_PATH,
//				request.getScheme() + "://" + request.getServerName() + ":"
//						+ request.getServerPort() + request.getContextPath()
//						+ "/");
		// xml请求解析
		Map<String, String> requestMap = MessageUtils.parseToMap(request.getInputStream());
		
		ReqBaseMessage reqMsg = MessageUtils.createFromReqMap(requestMap);
		DuplicateInfo dui = checkDuplicate(reqMsg);
		if(dui != null){
			for(WeChatListener listener:listeners){
				Method dm = ReflectUtils.getMethod("duplicateMessage",listener.getClass(), ReqBaseMessage.class,Object.class,int.class);
				if(checkAccept(dm, reqMsg)){
					listener.duplicateMessage(reqMsg, context, dui.getTimes());
				}
			}
			throw new DuplicateMessageException(dui);
		}
		Map<WeChatListener,Object> datas = new HashMap<WeChatListener,Object>();
		for(WeChatListener listener:listeners){
			Method bp = ReflectUtils.getMethod("beforeProcessMessage",listener.getClass(), ReqBaseMessage.class,Object.class);
			if(checkAccept(bp, reqMsg)){
				Object bdata = listener.beforeProcessMessage(reqMsg, context);
				datas.put(listener, bdata);
			}
		}
		RespBaseMessage respMsg = processor.execute(reqMsg,context);
		for(WeChatListener listener:listeners){
			Method ap = ReflectUtils.getMethod("afterProcessedMessage",listener.getClass(), RespBaseMessage.class, ReqBaseMessage.class,Object.class,Object.class);
			if(checkAccept(ap, reqMsg)){
				listener.afterProcessedMessage(respMsg, reqMsg, context,datas.get(listener));
			}
		}
		if(respMsg == null)
			return null;
		return MessageUtils.respMessageToXml(respMsg);
	}
	
	public void doMsg(HttpServletRequest request,HttpServletResponse response,Context context){
		Exception ex = null;
		try {
			doMsg_(request, response, context);
		} catch (Exception e) {
			e.printStackTrace();
			ex = e;
		}finally{
			for(WeChatInterceptor interceptor :interceptors){
				interceptor.afterComplection(context, ex);
			}
		}
	}
	@SuppressWarnings("deprecation")
	private void doMsg_(HttpServletRequest request,HttpServletResponse response,Context context)throws Exception{
		Map<String, String> requestMap = MessageUtils.parseToMap(request.getInputStream());
		
		
		ReqBaseMessage reqMsg = MessageUtils.createFromReqMap(requestMap);
		if(debug){
			LOG.debug("request \n"+MessageUtils.messageToXml(reqMsg));
		}
		
		//TODO 处理加密的消息
		
		DuplicateInfo dui = checkDuplicate(reqMsg);
		if(dui != null){
			for(WeChatListener listener:listeners){
				Method dm = ReflectUtils.getMethod("duplicateMessage",listener.getClass(), ReqBaseMessage.class,Object.class,int.class);
				if(checkAccept(dm, reqMsg)){
					listener.duplicateMessage(reqMsg, context, dui.getTimes());
				}
			}
			//处理重复消息
			if(debug && interceptors.isEmpty()){
				LOG.debug("\n\tduplicate message from "+reqMsg.getFromUserName()+" to "+reqMsg.getToUserName()+", type "+reqMsg.getMsgType()+" at "+new java.util.Date(reqMsg.getCreateTime()*1000));
				return;
			};
			for(WeChatInterceptor interceptor :interceptors){
				RespBaseMessage dmsg = interceptor.duplicateHandle(dui, context);
				if(dmsg == RespBaseMessage.NONE){
					LOG.info("拦截器拦截了消息。拦截器："+interceptor+"，消息源："+dui.getContent().getFromUserName());
					return;
				}else if(dmsg != null){
					printResult(response, dmsg);
					return;
				}
			}
		}
		for(WeChatInterceptor interceptor :interceptors){
			if(!interceptor.preHandle(reqMsg, request, response, context)){
				return;
			}
		}
		Map<WeChatListener,Object> datas = new HashMap<WeChatListener,Object>();
		beforePmsg(reqMsg, datas, context);
		
		RespBaseMessage respMsg = processor.execute(reqMsg,context);
		
		//调用监听器消息处理后的事件方法
		afterPmsg(reqMsg, respMsg, datas, context);
		//调用拦截器消息处理后方法
		for(WeChatInterceptor interceptor :interceptors){
			interceptor.postHandle(reqMsg, respMsg, request, response, context);
		}
		
		printResult(response, respMsg);
		
		//设置上一次的返回信息，可以用于有重复消息时可以直接返回上一次的处理结果
		String key = duplicateKey(reqMsg);
		DuplicateInfo dupI = duplicateMap.get(key);
		if(dupI != null){
			dupI.setRespMsg(respMsg);
		}
	}
	private void printResult(HttpServletResponse response,RespBaseMessage respMsg) throws IOException{
		if(respMsg == null)
			return;
		String result;
		if(respMsg instanceof RespXmlMessage){
			result = ((RespXmlMessage)respMsg).getXml();
		}else{
			result = MessageUtils.respMessageToXml(respMsg);
		}
		PrintWriter writer = null;
		try{
			 writer = response.getWriter();
		}catch (IOException e) {
			writer = new PrintWriter(response.getOutputStream());
		}
		if(debug)
			LOG.debug("response \n"+result);
		writer.print(result);
		writer.flush();
	}
	private void afterPmsg(ReqBaseMessage reqMsg,RespBaseMessage respMsg,Map<WeChatListener,Object> datas,Object params){
		for(WeChatListener listener:listeners){
			Method ap = ReflectUtils.getMethod("afterProcessedMessage",listener.getClass(), RespBaseMessage.class, ReqBaseMessage.class,Object.class,Object.class);
			if(checkAccept(ap, reqMsg)){
				listener.afterProcessedMessage(respMsg, reqMsg, params,datas.get(listener));
			}
		}
	}
	private void beforePmsg(ReqBaseMessage reqMsg,Map<WeChatListener,Object> datas,Object params){
		for(WeChatListener listener:listeners){
			Method bp = ReflectUtils.getMethod("beforeProcessMessage",listener.getClass(), ReqBaseMessage.class,Object.class);
			if(checkAccept(bp, reqMsg)){
				datas.put(listener, listener.beforeProcessMessage(reqMsg, params));
			}
		}
	}
	private String duplicateKey(ReqBaseMessage reqMsg){
		return reqMsg.getToUserName()+"-"+reqMsg.getFromUserName();
	}
	private DuplicateInfo checkDuplicate(ReqBaseMessage reqMsg){
		String key = duplicateKey(reqMsg);
		DuplicateInfo info = duplicateMap.get(key);
		if(info == null || info.getTimes() > 2){
			duplicateMap.put(key, new DuplicateInfo(reqMsg, 0));
			return null;
		}
		ReqBaseMessage last = info.getContent();
		if(reqMsg.getCreateTime() - last.getCreateTime() <= duplicateMessageInterval){
			DuplicateInfo dinfo = new DuplicateInfo(reqMsg, info.getTimes()+1);
			duplicateMap.put(key, dinfo);
			return dinfo;
		}else{
			duplicateMap.put(key, new DuplicateInfo(reqMsg, 0));
		}
		return null;
	}
	//检查方法是否接受该消息类型
	private boolean checkAccept(AnnotatedElement anmt,ReqBaseMessage message){
		try {
			AcceptTypes atypes = anmt.getAnnotation(AcceptTypes.class);
			if(atypes.value() != null)
			for(MessageType type:atypes.value()){
				if(type.check(message)){
					return true;
				}
			}
		} catch (NullPointerException e) {
			return anmt instanceof Method;
		}
		return false;
	}
	/**
	 * @param processor 消息处理器，由应用提供
	 */
	public void setProcessor(WeChatMessageProcessor processor) {
		this.processor = processor;
	}
	public void addWeChatListener(WeChatListener listener){
		listeners.add(listener);
	}
	public boolean removeWeChatListener(WeChatListener listener){
		return listeners.remove(listener);
	}
	public void addWeChatInterceptor(WeChatInterceptor interceptor){
		interceptors.add(interceptor);
	}
	public boolean removeWeChatInterceptor(WeChatInterceptor intercetor){
		return interceptors.remove(intercetor);
	}
	public void setListeners(List<WeChatListener> listeners) {
		this.listeners = listeners;
	}
	public void setDuplicateMessageInterval(int duplicateMessageInterval) {
		this.duplicateMessageInterval = duplicateMessageInterval;
	}
	public void setInterceptors(List<WeChatInterceptor> interceptors) {
		if(interceptors == null){
			throw new IllegalArgumentException("interceptors == null");
		}
		this.interceptors = interceptors;
	}
	/**
	 * @param debug
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
