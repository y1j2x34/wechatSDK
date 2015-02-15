package y1j2x34.wechat.pojo;

import java.io.Serializable;

/**
 * 微信用户基本信息模型
 * <table border="1" id="wechat_user_module" cellspacing="0" cellpadding="4" align="center" width="640px">
 * <caption>字段说明</caption>
<tbody><tr>
<th style="width:240px">字段
</th>
<th>说明
</th></tr>
<tr>
<td> subscribe
</td>
<td> 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
</td></tr>
<tr>
<td> openid
</td>
<td> 用户的标识，对当前公众号唯一
</td></tr>
<tr>
<td> nickname
</td>
<td> 用户的昵称
</td></tr>
<tr>
<td> sex
</td>
<td> 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
</td></tr>
<tr>
<td> city
</td>
<td> 用户所在城市
</td></tr>
<tr>
<td> country
</td>
<td> 用户所在国家
</td></tr>
<tr>
<td> province
</td>
<td> 用户所在省份
</td></tr>
<tr>
<td> language
</td>
<td> 用户的语言，简体中文为zh_CN
</td></tr>
<tr>
<td> headimgurl
</td>
<td> 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
</td></tr>
<tr>
<td> subscribe_time
</td>
<td> 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
</td></tr>
<tr>
	<td>unionId</td>
	<td>只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。</td>
</tr>
</tbody></table>
 * @author yangjianxin
 *
 */
public class WechatUser implements Serializable{
	private static final long serialVersionUID = -4436538094388493490L;
	private int userId;
	private String openid;
	private String nickname;
	private int sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	private int headimgsize;
	//关注时间，单位：s
	private long subscribe_time;
	private boolean subscribe;
	//用户状态
	private int status;
	
	private String unionId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int user_id) {
		this.userId = user_id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public long getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public boolean isSubscribe() {
		return subscribe;
	}
	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}
	public int getHeadimgsize() {
		return headimgsize;
	}
	public void setHeadimgsize(int headimgsize) {
		this.headimgsize = headimgsize;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * <pre>
	 * <font size="3" ><b>UnionID机制说明：</b></font>
	 * 开发者可通过OpenID来获取用户基本信息。特别需要注意的是，如果开发者拥有多个移动应用、网站应用和公众帐号，
	 * 可通过获取用户基本信息中的unionid来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站
	 * 应用和公众帐号，用户的unionid是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
	 * </pre>
	 * @return
	 */
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
}
