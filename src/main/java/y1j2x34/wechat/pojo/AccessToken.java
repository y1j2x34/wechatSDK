package y1j2x34.wechat.pojo;
/**
 * 微信通用接口凭证
 * @author yangjianxin
 * @date 2013-12-26
 */
public class AccessToken {
	private String token;
	private int expiresIn;
	
	/**大约在2014年12月，凭证长度会增长到512个字符空间
	 * @return 接口调用凭证
	 * @see #setToken(String)
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token
	 * @see #getToken()
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return 凭证的有效时间
	 * @see #setExpiresIn(String)
	 */
	public int getExpiresIn() {
		return expiresIn;
	}
	/**
	 * @param expiresIn
	 * @see #getExpiresIn()
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
