package y1j2x34.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


public final class ReflectUtils {
	private ReflectUtils(){}
	
	/**
	 * 穷举获取类及其父类声明的字段
	 * @param name
	 * @param type
	 * @return
	 */
	public static final Field getField(String name,Class<?> type){
		try {
			return type.getDeclaredField(name);
		} catch (Exception e) {
			try {
				return type.getField(name);
			} catch (Exception e2) {
				Class<?> superClass = type.getSuperclass();
				if(superClass != null)
					return getField(name, superClass);
			}
		}
		return null;
	}
	/**
	 * 获得所有接口
	 * @param cls
	 * @return
	 */
	public static final Class<?>[] getAllInterfaces(Class<?> cls){
		Collection<Class<?>> interfaces = new HashSet<Class<?>>();
		Class<?> c = cls;
		while(c != null && c != Object.class ){
			interfaces.addAll(Arrays.asList(c.getInterfaces()));
			c = c.getSuperclass();
		}
		return interfaces.toArray(new Class<?>[interfaces.size()]);
	}
	/**
	 * 穷举获取类及其父类（不包括<code>Object<code>类）定义了的方法
	 * @param name
	 * @param type
	 * @param paramTypes
	 * @return
	 */
	public static final Method getMethod(String name,Class<?> type,Class<?>...paramTypes){
		try {
			return type.getDeclaredMethod(name, paramTypes);
		} catch (Exception e) {
			try {
				return type.getMethod(name, paramTypes);
			}catch(Exception e2){
				Class<?> superClass = type.getSuperclass();
				if(superClass != null)
					return getMethod(name, superClass, paramTypes);
			}
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public static final <T> T convert(String str,Class<? extends T> type){
		if(type == String.class){
			return (T)str;
		}else if(type == Integer.class || type == int.class){
			return (T)new Integer(Integer.parseInt(str));
		}else if(type == Long.class || type == long.class){
			return (T)new Long(Long.parseLong(str));
		}else if(type == Double.class || type == double.class){
			return (T)new Double(Double.parseDouble(str));
		}
		return null;
	}
}
