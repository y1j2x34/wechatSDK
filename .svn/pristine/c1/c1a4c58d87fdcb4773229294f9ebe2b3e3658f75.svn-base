package y1j2x34.wechat;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import y1j2x34.wechat.expt.UnknownMsgTypeException;
import y1j2x34.wechat.message.req.ReqBaseMessage;
import y1j2x34.wechat.message.resp.RespBaseMessage;
import y1j2x34.wechat.utils.ClassUtil;

public class DefaultMessageProcessor implements WeChatMessageProcessor{
	private Map<String,WeChatMessageListener<? extends ReqBaseMessage>> listeners = new HashMap<String,WeChatMessageListener<? extends ReqBaseMessage>>();
	private RespBaseMessage onErrorMsg;
	private RespBaseMessage defaultMsg;
	@Override
	public RespBaseMessage execute(ReqBaseMessage reqMsg, Context context)
			throws UnknownMsgTypeException {
		try{
			String className = reqMsg.getClass().getSimpleName();
			@SuppressWarnings("unchecked")
			WeChatMessageListener<ReqBaseMessage> listener = (WeChatMessageListener<ReqBaseMessage>) listeners.get(className);
			if(listener != null){
				return listener.onReceive(reqMsg, context);
			}
		}catch(Throwable t){
			t.printStackTrace();
			return onErrorMsg;
		}
		return defaultMsg;
	}
	public void setListeners(
			Map<String, WeChatMessageListener<? extends ReqBaseMessage>> listeners) {
		this.listeners.putAll(listeners);
	}
	public Map<String, WeChatMessageListener<? extends ReqBaseMessage>> getListeners() {
		return listeners;
	}
	public WeChatMessageListener<? extends ReqBaseMessage> addListener(String name,WeChatMessageListener<? extends ReqBaseMessage> listener){
		return listeners.put(name, listener);
	}
	public void setScanPackage(String packageName){
		if(StringUtils.isEmpty(packageName)){
			throw new IllegalArgumentException("empty package name");
		}
		doScan(packageName);
	}
	private void doScan(String pack){
		Set<Class<?>> listenerClasses = ClassUtil.getClasses(pack, WeChatMessageListener.class, true);
		if(listenerClasses.size() == 0) return;
		loop:for(Class<?> clazz:listenerClasses){
			try {
				Class<?>[] types = ClassUtil.getParamTypes(clazz);
				String name = null;
				if(types.length == 1){
					name = types[0]==null?null:types[0].getSimpleName();
				}
				if(name == null) continue loop;
				Constructor<?> constr = clazz.getConstructor();
				constr.setAccessible(true);
				this.listeners.put(name, (WeChatMessageListener<?>)constr.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
