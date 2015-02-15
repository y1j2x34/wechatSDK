package y1j2x34.wechat;

import java.io.Serializable;

/**
 * 存储开发者凭据信息
 * 
 * @author yangjianxin
 */
public abstract class Credential implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 开发者凭据——建议使用硬编码方式
	 * 
	 * @return
	 */
	public abstract String getAppId();

	/**
	 * @see #getAppId()
	 * @return
	 */
	public abstract String getAppSecret();
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + getAppId() == null?0:getAppId().hashCode();
		result = 31 * result + getAppSecret() == null?0:getAppSecret().hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null)return false;
		if(obj == this)return true;
		if(!(obj instanceof Credential)){
			return false;
		}
		Credential o = (Credential)obj;
		return (o.getAppId() == getAppId() || (getAppId() != null && getAppId().equals(o.getAppId())))
				&& (getAppSecret() == o.getAppSecret() || (getAppSecret() != null && getAppSecret().equals(o.getAppSecret())));
	}
}