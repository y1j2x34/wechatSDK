package y1j2x34.wechat.utils;
/**
 * 类过滤器，用来扫描过滤“类”
 * @see ClassUtil#findClassesInPackageByFile(String, String, boolean, java.util.Set, ClassFilter)
 * @author yangjianxin
 * @date 2014-11-19 下午1:54:54
 */
public interface ClassFilter {
	boolean accept(Class<?> clazz);
}
