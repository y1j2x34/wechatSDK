package y1j2x34.wechat;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultContext extends HashMap<Object,Object> implements Context,Map<Object,Object>{

	private static final long serialVersionUID = 1L;
	public DefaultContext() {
	}
	public DefaultContext(Object key,Object value) {
		set(key, value);
	}
	@Override
	public Context set(Object key, Object value) {
		put(key, value);
		return this;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Object get(Object key) {
		return super.get(key);
	}
	@Override
	public Context addFrom(Map<Object, Object> map) {
		if(map == null){
			throw new NullPointerException();
		}
		putAll(map);
		return this;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Context addFrom(Object pojo) {
		if(pojo instanceof Map){
			return addFrom((Map<Object,Object>)pojo);
		}else if(pojo instanceof Context){
			return addFrom((Context)pojo);
		}
		if(pojo == null){
			throw new NullPointerException();
		}
		try {
			BeanInfo info = Introspector.getBeanInfo(pojo.getClass());
			PropertyDescriptor[] pds = info.getPropertyDescriptors();
			if(pds != null && pds.length > 0){
				for(int i=0;i<pds.length;i++){
					PropertyDescriptor pd = pds[i];
					Method readMethod = pd.getReadMethod();
					if(readMethod.getDeclaringClass() == Object.class){
						continue;
					}
					if(!Modifier.isPublic(readMethod.getModifiers())){
						readMethod.setAccessible(true);
					}
					try{
						Object obj = readMethod.invoke(pojo);
						set(pd.getDisplayName(),obj);
					}catch(Exception e){}
				}
			}
			
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return this;
	}
	@Override
	public Set<Object> keyset() {
		return super.keySet();
	}
	@Override
	public Context addFrom(Context context) {
		if(context == null){
			throw new NullPointerException();
		}
		Set<Object> keys = context.keyset();
		for(Object key:keys){
			set(key, context.get(key));
		}
		return this;
	}
}
