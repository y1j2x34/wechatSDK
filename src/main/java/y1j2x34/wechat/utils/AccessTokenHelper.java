package y1j2x34.wechat.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import y1j2x34.wechat.Credential;
import y1j2x34.wechat.expt.WeChatException;
import y1j2x34.wechat.pojo.AccessToken;

public class AccessTokenHelper {
	/**
	 * 接口调用凭证元数据，包含了：凭证创建时间和凭证内容，可以校验凭证是否超时,方便管理凭证缓存
	 * @see #isTimeout()
	 * @author yangjianxin
	 * @date 2014-11-19 下午1:24:05
	 */
	public static class MetaData{
		private final long mTime;
		private final AccessToken mToken;
		protected MetaData(long time,AccessToken atoken) {
			mTime = time;
			mToken = atoken;
		}
		/**
		 * 检查凭证是否超时
		 * @return
		 */
		public final boolean isTimeout(int advance){
			return mToken.getExpiresIn()-advance <= MessageUtils.currentCreateTime() - mTime;
		}
	}
	/**
	 * <pre>
	 * access_token缓存处理接口
	 * {@link AccessTokenHelper#AccessTokenHelper()} 使用默认的HashMap缓存
	 * 如果有需要使用其它方式，可自行定义实现类并使用构造器：{@link AccessTokenHelper#AccessTokenHelper(AccessTokenCacheHandler)}
	 * </pre>
	 * @author yangjianxin
	 * @date 2014-11-19 下午1:27:30
	 */
	public static interface AccessTokenCacheHandler{
		/**
		 * 获取调用凭证
		 * @param credential
		 * @return
		 */
		MetaData read(Credential credential);
		/**
		 * <pre>
		 * 根据开发者凭据信息缓存微信凭证，方便同一个平台支持多个微信公众号
		 * 使用缓存可以减少调用获取凭证接口次数，减少资源消耗
		 * 以及最重要的，频繁刷新access_token会导致api调用受限！
		 * </pre>
		 * @param data
		 * @param credential
		 * @return
		 */
		MetaData cache(MetaData data,Credential credential);
	}
	
	private final AccessTokenCacheHandler handler;
	private int advance = 0;
	
	public AccessTokenHelper(AccessTokenCacheHandler handler) {
		if(handler == null)
			throw new IllegalArgumentException("handler == null");
		this.handler = handler;
	}
	public AccessTokenHelper() {
		this(new AccessTokenCacheHandler() {
			private Map<Credential,MetaData> caches = new HashMap<Credential,MetaData>();
			@Override
			public synchronized MetaData read(Credential credential) {
				return caches.get(credential);
			}
			@Override
			public synchronized MetaData cache(MetaData data, Credential credential) {
				clear();
				return caches.put(credential, data);
			}
			private void clear(){
				for(Entry<Credential,MetaData> entry:new HashSet<Entry<Credential,MetaData>>(caches.entrySet())){
					if(entry.getValue().isTimeout(0)){//移除超时的凭证
						caches.remove(entry.getKey());
					}
				}
			}
		});
	}
	/**
	 * @throws IllegalArgumentException if credential invalid(credential == null || appid is empty || appSecrete is empty)
	 * @param credential
	 * @return null if 
	 */
	public AccessToken get(Credential credential){
		if(credential == null || StringUtils.isEmpty(credential.getAppId()) || StringUtils.isEmpty(credential.getAppSecret()))
			throw new IllegalArgumentException("invalid credential!");
		MetaData md = handler.read(credential);
		if(md == null || md.mToken == null || md.isTimeout(advance)){
			md = _reset(credential);
		}
		return md.mToken;
	}
	private MetaData _reset(Credential credential){
		long now = MessageUtils.currentCreateTime();
		AccessToken accessToken = null;
		try{
			accessToken = WeChatUtil.getAccessToken(credential);
		}catch(WeChatException e){
			throw new RuntimeException(e);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		MetaData md = new MetaData(now, accessToken);
		handler.cache(md, credential);
		return md;
	}
	public void reset(Credential credential){
		_reset(credential);
	}
	/**
	 * 允许提前超时时间，单位：s
	 * @param advance default 0
	 * @throws IllegalArgumentException if advance < 0 or advance >= 7200
	 */
	public void setAdvance(int advance){
		if(advance < 0 || advance >= 7200){
			throw new IllegalArgumentException("illegal advance :"+advance);
		}
		this.advance = advance;
	}
}
