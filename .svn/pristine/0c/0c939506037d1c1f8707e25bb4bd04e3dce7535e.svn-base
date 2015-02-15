package y1j2x34.wechat.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

public class ClassUtil {
	private static final Logger log = Logger.getLogger(ClassUtil.class);
	public static final class RegexClassNameFilter implements ClassFilter{
		private String regex;
		public RegexClassNameFilter(String regex) {
			this.regex = regex;
		}
		@Override
		public boolean accept(Class<?> clazz) {
			return clazz != null && clazz.getSimpleName().matches(regex);
		}
	}
	public static final class SupClassFilter implements ClassFilter{
		private Class<?> superClass;
		public SupClassFilter(Class<?> superClass) {
			this.superClass = superClass;
		}
		@Override
		public boolean accept(Class<?> clazz) {
			return superClass != null && clazz != null && superClass.isAssignableFrom(clazz);
		}
	}
	public static Class<?>[] getParamTypes(Class<?> clazz){
		Type[] intfs = clazz.getGenericInterfaces();
		if(intfs.length == 1 && intfs[0] instanceof ParameterizedType){
			ParameterizedType ptype = (ParameterizedType)intfs[0];
			Type[] types = ptype.getActualTypeArguments();
			List<Class<?>> result = new ArrayList<Class<?>>(types.length);
			for(Type type:types){
				if(type instanceof Class){
					result.add((Class<?>)type);
				}
			}
			return result.toArray(new Class[result.size()]);
		}
		return new Class[0];
	}
	/**
	 * 获得所有类
	 * @param pack
	 * @param recursion
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack,boolean recursion){
		return getClasses(pack, (ClassFilter)null,recursion);
	}
	/**
	 * 获得某个类在当前包下的子类
	 * @param pack
	 * @param superClass
	 * @param recursion
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack,Class<?> superClass,boolean recursion){
		return getClasses(pack, new SupClassFilter(superClass),recursion);
	}
	public static Set<Class<?>> getClasses(String pack,String regex,boolean recursion){
		return getClasses(pack, new RegexClassNameFilter(regex),recursion);
	}
	@SuppressWarnings("unchecked")
	private static final Set<Class<?>> EMPTY_SET = Collections.EMPTY_SET;
	/**
	 * 扫描类，会自动区分到jar包和硬盘文件
	 * @param packageName
	 * @param filter
	 * @param recursion
	 * @return
	 */
	public static Set<Class<?>> getClasses(String packageName,ClassFilter filter,boolean recursion){
		if(packageName == null) return EMPTY_SET;
		Set<Class<?>> classes = new HashSet<Class<?>>();
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> dirs = null;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
		} catch (IOException e) {
			e.printStackTrace();
			return EMPTY_SET;
		}
		while (dirs.hasMoreElements()) {
			URL url = (URL) dirs.nextElement();
			//获得协议
			String protocol = url.getProtocol();
			//以文件形式保存在服务器上的
			if("file".equals(protocol)){
				try {
					String filePath = URLDecoder.decode(url.getFile(), "utf-8");
					findClassesInPackageByFile(packageName, filePath, recursion, classes, filter);
				} catch (UnsupportedEncodingException e) {}
			}else if("jar".equals(protocol)){
				JarFile jar;
				try {
					jar = ((JarURLConnection)url.openConnection()).getJarFile();
					Enumeration<JarEntry> entries = jar.entries();
					while (entries.hasMoreElements()) {
						JarEntry entry = (JarEntry) entries.nextElement();
						String name = entry.getName();
						if(name.charAt(0) == '/'){
							name = name.substring(1);
						}
						if(name.startsWith(packageDirName)){
							int idx = name.lastIndexOf('/');
							if(idx == -1){
								packageName = name.substring(0,idx).replace('/', '.');
							}
							if(idx != -1 || recursion){
								if(!entry.isDirectory() && name.endsWith(".class")){
									String className = name.substring(packageName.length() + 1, name.length() - 6);
									try {
										Class<?> clazz = Class.forName(packageName+'.'+className);
										if(filter.accept(clazz)){
											classes.add(clazz);
										}
									} catch (ClassNotFoundException e) {
										log.error(e.getMessage(),e);
									}
								}
							}
						}
					}
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}
		return classes;
	}
	/**
	 * 以文件方式扫描类
	 * @param packageName	包名
	 * @param filePath	文件路径
	 * @param recursive	是否递归到子目录下
	 * @param classes	存储结果集合
	 */
	public static void findClassesInPackageByFile(String packageName,String packagePath,final boolean recursive,Set<Class<?>> classes,ClassFilter filter){
		File dir = new File(packagePath);
		if(!dir.exists() || !dir.isDirectory()){
			log.warn(""+packageName+"下没有任何文件");
			return;
		}
		File[] dirFiles = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || file.getName().endsWith(".class");
			}
		});
		for(File file:dirFiles){
			if(file.isDirectory()){
				findClassesInPackageByFile(packageName+"."+file.getName(), file.getPath(), recursive, classes, filter);
			}else{
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					className = packageName+"."+className;
					Class<?> clazz = Class.forName(className);
					if(filter.accept(clazz)){
						classes.add(clazz);
					}
				} catch (ClassNotFoundException e) {
					log.error(e.getMessage(),e);
				} catch(Error e){
					log.error(e.getMessage(),e);
					e.printStackTrace();
				}
			}
		}
	}
}
