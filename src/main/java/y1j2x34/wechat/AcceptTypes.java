package y1j2x34.wechat;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 标记方法接受的消息类型
 * @author y1j2x34
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface AcceptTypes {
	/**
	 * 
	 * @return
	 */
	MessageType[] value();
}
