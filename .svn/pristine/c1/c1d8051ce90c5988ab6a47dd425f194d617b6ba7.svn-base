package y1j2x34.wechat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import y1j2x34.wechat.expt.WeChatException;
import y1j2x34.wechat.pojo.AccessToken;
/**
 * 应用上下文，用来获取微信公众平台应用的全局信息
 * @author yangjianxin
 */
public abstract class AppContext {
	protected static final Logger LOG = Logger.getLogger(AppContext.class);
	/**
	 * 用于获得当前微信应用的url
	 * @see #data(String)
	 */
	public static final String DATA_BASE_PATH = "basePath";
	private static final class Holder{
		private static volatile boolean initial = false;
		private static AppContext INSTANCE;
	}
	public static void init(AppContext o){
		if(Holder.initial){
			throw new IllegalStateException("app context initialized!");
		}
		Holder.INSTANCE = o;
		Holder.initial = true;
	}
	public static AppContext getInstance(){
		return Holder.INSTANCE;
	}
	private Map<String,Object> datas = Collections.synchronizedMap(new HashMap<String,Object>());
	private Properties codeProp = new Properties();
	{
		loadReturnCodeProperty();
	}
	/**
	 * 微信接口调用返回消息编码
	 * @return 
	 */
	public Charset getCharset(){return Charset.forName("utf-8");};
	
	/**
	 * 用于本地存储微信消息的根目录,如果不需要，可以不设置
	 * @return
	 */
	public String getRootFolder(){return "";}
	/**
	 * 全局唯一票据，公众号调用各接口时都需使用access_token。正常情况下access_token有效期为7200秒
	 * @param credential 公众平台开发则凭证信息
	 * @return
	 */
	public abstract AccessToken getAccessToken(Credential credential);
	
	
	/**
	 * 获取全局变量
	 * @param id 变量名
	 * @return 变量值，不存在时返回<code>null</code>
	 */
	@SuppressWarnings("unchecked")
	public <D> D data(String id) {
		return (D)datas.get(id);
	}
	
	/**
	 * 设置全局变量
	 * @param id 变量名
	 * @param data 变量值
	 * @return
	 */
	public Object data(String id, Object data) {
		return datas.put(id, data);
	}
	
	/**
	 * 加载微信返回码配置
	 */
	protected void loadReturnCodeProperty(){
		try {
			codeProp.load(AppContext.class.getClassLoader().getResourceAsStream(returnCodePropertyFile()));
		} catch (IOException e) {
			LOG.error("返回码加载异常。");
			LOG.error(e.getMessage(),e);
		}
		LOG.info(codeProp.toString());
	}
	/**
	 * 公众号每次调用接口时，可能获得正确或错误的返回码，开发者可以根据返回码信息调试接口
	 * @see WeChatException#getErrorCode()
	 * @param code 返回码
	 * @return 返回码信息
	 */
	public String getWechatErrorMessage(int code) {
		return codeProp.getProperty(String.valueOf(code));
	}
	/**
	 * 微信返回码配置文件
	 * @return
	 */
	protected String returnCodePropertyFile(){
		return "retn_code.properties";
	}
	public boolean invalidateAccessToken(Credential credential){
		return false;
	}
}
