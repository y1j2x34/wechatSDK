package y1j2x34.wechat;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import y1j2x34.wechat.pojo.AccessToken;
import y1j2x34.wechat.utils.AccessTokenHelper;
/**
 * 微信公众平台应用初始化监听器
 * 初始化参数：
 * <ul>
 *       <li>folder      文件缓存地址</li>
 *       <li>encoding 编码</li>
 *       <li>tokenAdvance access_token提前失效时间</li>
 * </ul>
 * @author yangjianxin
 * @date 2014-12-12 下午7:14:42
 */
public class WechatPrepareListener implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			
			ServletContext sctx = sce.getServletContext();
			
			String cfg_encoding = sctx.getInitParameter("encoding");
			String cfg_folder = sctx.getInitParameter("folder");
			String cfg_advance = sctx.getInitParameter("tokenAdvance");
			
			sctx.removeAttribute("encoding");
			sctx.removeAttribute("folder");
			
			final String encoding = cfg_encoding != null && Charset.isSupported(cfg_encoding.trim())?cfg_encoding.trim():"utf-8";
			final String folder = cfg_folder==null?null:cfg_encoding.trim();
			final int advance = cfg_advance==null?0:Integer.parseInt(cfg_advance);
			
			Method m = AppContext.class.getDeclaredMethod("init",AppContext.class);
			m.setAccessible(true);
			
			m.invoke(null, new AppContext(){
				private final Properties codeProp = new Properties();
				private Map<String,Object> datas = Collections.synchronizedMap(new HashMap<String,Object>());
				private final AccessTokenHelper tokenHelper = new AccessTokenHelper();
				private final Charset charset;
				{
					codeProp.load(AppContext.class.getClassLoader().getResourceAsStream("retn_code.properties"));
					charset = Charset.forName(encoding);
					tokenHelper.setAdvance(advance);
				}
				@SuppressWarnings("unchecked")
				@Override
				public <D> D data(String key) {
					return (D)datas.get(key);
				}
				@Override
				public Object data(String key, Object value) {
					return datas.put(key, value);
				}
				@Override
				public AccessToken getAccessToken(Credential credential) {
					return tokenHelper.get(credential);
				}
				@Override
				public Charset getCharset() {
					return charset;
				}
				@Override
				public String getRootFolder() {
					return folder;
				}
				@Override
				public String getWechatErrorMessage(int code) {
					return codeProp.getProperty(String.valueOf(code));
				}
				@Override
				public boolean invalidateAccessToken(Credential credential) {
					AccessToken token = getAccessToken(credential);
					if(token != null){
						token.setExpiresIn(0);
						return true;
					}
					return false;
				}
			});
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
