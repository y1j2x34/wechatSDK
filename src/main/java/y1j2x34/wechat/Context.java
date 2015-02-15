package y1j2x34.wechat;

import java.util.Map;
import java.util.Set;
/**
 * 一次请求的上下文信息
 * @author yangjianxin
 * @date 2014-11-19 下午4:54:27
 */
public interface Context {
	
	Context set(Object key,Object value);
	
	<T> T get(Object key);
	/**
	 * @throws NullPointerException if <tt>map</tt> is null
	 * @param map
	 * @return
	 */
	Context addFrom(Map<Object,Object> map);
	/**
	 * @throws NullPointerException if <tt>pojo</tt> is null
	 * @param pojo
	 * @return
	 */
	Context addFrom(Object pojo);
	
	Context addFrom(Context context);
	
	Set<Object> keyset();
	
}
