package y1j2x34.wechat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import y1j2x34.utils.HttpUtils;
import y1j2x34.utils.RequestException;
import y1j2x34.utils.HttpUtils.Data;
import y1j2x34.utils.HttpUtils.ReadOnlyConnection;
import y1j2x34.utils.HttpUtils.RequestParams;
import y1j2x34.utils.HttpUtils.Response;
import y1j2x34.wechat.API;
import y1j2x34.wechat.AppContext;
import y1j2x34.wechat.Credential;
import y1j2x34.wechat.WeChatAPILogger;
import y1j2x34.wechat.expt.WeChatException;
import y1j2x34.wechat.message.mass.MassFilter;
import y1j2x34.wechat.message.mass.MassGroupImageMsg;
import y1j2x34.wechat.message.mass.MassGroupMsg;
import y1j2x34.wechat.message.mass.MassGroupNewsMsg;
import y1j2x34.wechat.message.mass.MassGroupTextMsg;
import y1j2x34.wechat.message.mass.MassGroupVideoMsg;
import y1j2x34.wechat.message.mass.MassGroupVoiceMsg;
import y1j2x34.wechat.message.mass.MassImage;
import y1j2x34.wechat.message.mass.MassMPNews;
import y1j2x34.wechat.message.mass.MassMPVideo;
import y1j2x34.wechat.message.mass.MassMsgBody;
import y1j2x34.wechat.message.mass.MassOPListImageMsg;
import y1j2x34.wechat.message.mass.MassOPListMsg;
import y1j2x34.wechat.message.mass.MassOPListNewsMsg;
import y1j2x34.wechat.message.mass.MassOPListTextMsg;
import y1j2x34.wechat.message.mass.MassOPListVideoMsg;
import y1j2x34.wechat.message.mass.MassOPListVoiceMsg;
import y1j2x34.wechat.message.mass.MassText;
import y1j2x34.wechat.message.mass.MassVideoInfo;
import y1j2x34.wechat.message.mass.MassVoice;
import y1j2x34.wechat.message.mass.VideoInfo;
import y1j2x34.wechat.message.mass.prev.MassPreviewImageMsg;
import y1j2x34.wechat.message.mass.prev.MassPreviewMsg;
import y1j2x34.wechat.message.mass.prev.MassPreviewNewsMsg;
import y1j2x34.wechat.message.mass.prev.MassPreviewTextMsg;
import y1j2x34.wechat.message.mass.prev.MassPreviewVideoMsg;
import y1j2x34.wechat.message.mass.prev.MassPreviewVoiceMsg;
import y1j2x34.wechat.message.svc.SvBaseMessage;
import y1j2x34.wechat.pojo.AccessToken;
import y1j2x34.wechat.pojo.BaseGroup;
import y1j2x34.wechat.pojo.Button;
import y1j2x34.wechat.pojo.CUsr_Data;
import y1j2x34.wechat.pojo.ComplexButton;
import y1j2x34.wechat.pojo.ConcernedUsers;
import y1j2x34.wechat.pojo.EventButton;
import y1j2x34.wechat.pojo.Groups;
import y1j2x34.wechat.pojo.MediaData;
import y1j2x34.wechat.pojo.Menu;
import y1j2x34.wechat.pojo.Ticket;
import y1j2x34.wechat.pojo.URLMediaData;
import y1j2x34.wechat.pojo.UploadNews;
import y1j2x34.wechat.pojo.UploadResult;
import y1j2x34.wechat.pojo.UserGroup;
import y1j2x34.wechat.pojo.ViewButton;
import y1j2x34.wechat.pojo.WechatUser;

public class WeChatUtil {
	
	private static final String ACCESS_TOKEN_NAME = "access_token";
	private static final String ERRCODE = "errcode";
	private static final String ERRMSG = "errmsg";
	
	private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	private static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	private static final String USR_INF_URL = "https://api.weixin.qq.com/cgi-bin/user/info";
	private static final String USR_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get";
	private static final String UPDATE_USER_REMARK_URL = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark";
	private static final String CREATE_NEW_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/create";
	private static final String GET_GROUP_ID_URL = "https://api.weixin.qq.com/cgi-bin/groups/getid";
	private static final String GET_ALL_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/get"; 
	/////////////////////////////////
	private static final String DELETE_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/delete";
	/////////////////////////////////
	private static final String MOVE_USR_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update";
	private static final String RENAME_USR_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/update";
	private static final String SEND_SERVICE_NEWS_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	private static final String GET_OPENID_BY_CODE = "https://api.weixin.qq.com/sns/oauth2/access_token?";
	private static final String GET_USER_MENUS_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";
	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";
	private static final String DOWNLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get";
	private static final String CREATE_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
	private static final String SHOW_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
	private static final String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?";
	private static final String UPLOAD_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
	private static final String MASS_UPLOAD_VIDEO_URL = "https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?";
	private static final String MASS_SEND_ALL_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?";
	private static final String MASS_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?";
	private static final String MASS_DELETE_URL = "https://api.weixin.qq.com//cgi-bin/message/mass/delete?"; 
	private static final String MASS_PREVIEW_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
	private static final Logger LOG = Logger.getLogger(WeChatUtil.class);
	
	public static WeChatAPILogger logger;
	
	private static volatile boolean silence = false;
	
	/**
	 * 微信API调用日志开关
	 * @param silence
	 */
	public static void keepSilence(boolean silence) {
		WeChatUtil.silence = silence;
	}
	
	private static void log(API api,Object req,Throwable t){
		if(silence || logger == null) return;
		logger.log(api, req, t);
	}
	private static void log(API api,Object req,Object resp){
		if(silence || logger == null) return;
		logger.log(api, req, resp);
	}
	private static void log(API api,Object req){
		if(silence || logger == null) return;
		logger.log(api, req);
	}
	/**
	 * 获取通用接口凭证
	 * <hr/>
	 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96access_token">获取access_token</a>
	 * @param appid
	 * @param appsecret
	 * @return
	 * @throws Exception
	 */
	public static AccessToken getAccessToken(Credential credential)throws WeChatException,RequestException,IOException{
		if(credential == null){
			throw new IllegalArgumentException("credential == null");
		}
		AccessToken access_token = null;
		RequestParams param = new RequestParams();
		param
		.setUrl(GET_ACCESS_TOKEN_URL)
		.addQuery("grant_type","client_credential")
		.addQuery("appid",credential.getAppId())
		.addQuery("secret",credential.getAppSecret());
		
		String result = HttpUtils.get(param).getResponseText(AppContext.getInstance().getCharset());
		
		LOG.info("获取access_token: "+result);
		JSONObject jo = check_null_errcode_result(result);
		access_token = new AccessToken();
		access_token.setToken(jo.getString(ACCESS_TOKEN_NAME));
		access_token.setExpiresIn(jo.getInt("expires_in"));
		return access_token;
	}
	/**
	 * 获取关注的用户列表
	 * <hr/>
	 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E5%85%B3%E6%B3%A8%E8%80%85%E5%88%97%E8%A1%A8">获取关注者列表</a>
	 * @param token 接口调用凭证
	 * @param next_openid 第一个拉取的OPENID，不填默认从头开始拉取
	 * @return
	 * @throws Exception
	 */
	public static ConcernedUsers getCUsrs(AccessToken token,String next_openid)throws WeChatException,RequestException,IOException{
		checkToken(token);
		checkNull(next_openid, "next_openid == null");
		
		RequestParams params = new RequestParams();
		params.setUrl(USR_LIST_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		if(next_openid != null){
			params.addQuery("next_openid",next_openid);
		}
		Response resp = HttpUtils.get(params);
		
		String result = resp.getResponseText(AppContext.getInstance().getCharset());
		
		JSONObject jo = check_null_errcode_result(result);
		
		ConcernedUsers users = new ConcernedUsers();
		users.setCount(jo.getInt("count"));
		users.setTotal(jo.getInt("total"));
		users.setNext_openid(jo.getString("next_openid"));
		users.setData(new CUsr_Data());
		if(jo.containsKey("data")){
			JSONObject jdata = jo.getJSONObject("data");
			
			if(jdata.containsKey("openid")){
				@SuppressWarnings("unchecked")
				ArrayList<Object> arr = new ArrayList<Object>(jdata.getJSONArray("openid"));
				String[] openid = new String[arr.size()];
				users.getData().setOpenid(arr.toArray(openid));
			}
		}
		return users;
	}
	/**
	 * 获取微信用户信息
	 * <hr/>
	 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E5%9F%BA%E6%9C%AC%E4%BF%A1%E6%81%AF(UnionID%E6%9C%BA%E5%88%B6)">获取用户基本信息(UnionID机制)</a>
	 * @param openid 普通用户标识，对当前公众号唯一
	 * @param lang 国家地区语言版本 zh_CN 简体、zh_TW繁体、en英语
	 * @param token 接口调用凭证
	 * @return
	 * @throws WeChatException
	 * @throws RequestException
	 * @throws IOException
	 */
	public static WechatUser getUser(String openid,String lang,AccessToken token)throws WeChatException,RequestException,IOException{
		checkNull(openid, "openid == null");
		checkToken(token);
		
		RequestParams param = new RequestParams();
		param.setUrl(USR_INF_URL)
		.addQuery(ACCESS_TOKEN_NAME, token.getToken())
		.addQuery("openid", openid)
		.addQuery("lang", lang);
		
		Response resp = HttpUtils.get(param);
		String result = resp.getResponseText(AppContext.getInstance().getCharset());
		
		LOG.info("获取"+openid+"的用户信息返回结果："+result);
		
		JSONObject jo = check_null_errcode_result(result);
		
		WechatUser user = new WechatUser();
		user.setSubscribe(jo.getInt("subscribe")==1);
		jo.remove("subscribe");
		String headimgurl = jo.getString("headimgurl");
		
		user.setHeadimgsize(getImageSize(headimgurl));
		
		user.setSubscribe_time(jo.getLong("subscribe_time"));
		jo.remove("subscribe_time");
		@SuppressWarnings("unchecked")
		Set<Entry<String,Object>> entrySet = jo.entrySet();
		for(Entry<String,Object> entry:entrySet){
			try {
				Field f = WechatUser.class.getDeclaredField(entry.getKey());
				f.setAccessible(true);
				if(f.getType() == String.class){
					f.set(user, jo.getString(entry.getKey()));
				}else if(f.getType() == int.class){
					f.set(user,jo.getInt(entry.getKey()));
				}else{
					LOG.info("无法转换"+f.getName()+",类型："+f.getType().getName()+",键："+entry.getKey()+"，值："+entry.getValue());
				}
			} catch (Exception e) {}
		}
		return user;
	}
	/**
	 * 通过openid获得用户信息
	 * <hr/>
	 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E5%9F%BA%E6%9C%AC%E4%BF%A1%E6%81%AF(UnionID%E6%9C%BA%E5%88%B6)">获取用户基本信息(UnionID机制)</a>
	 * default lang == zh_CN
	 * @see #getUser(String, String, AccessToken)
	 * @param openid
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static WechatUser getUser(String openid,AccessToken token)throws WeChatException,RequestException,IOException{
		return getUser(openid, "zh_CN", token);
	}
	/**
	 * 根据图片url获取图片大小，
	 * @param imageUrl
	 * @return -1 表示 获取失败
	 */
	public static int getImageSize(String imageUrl){
		if(imageUrl != null && imageUrl.length() > 0)
		{
			int li = imageUrl.lastIndexOf('/');
			if(li != -1){
				try {
					int s = Integer.parseInt(imageUrl.substring(li+1));
					return s==0?640:s;
				} catch (NumberFormatException e) {
					return 640;
				}
			}
		}
		return -1;
	}
	/**
	 * 创建分组
	 * @param name
	 * @param token
	 * @return
	 * @throws HttpsRequestException
	 * @throws Exception
	 */
	public static BaseGroup createNewGroup(String name,AccessToken token)throws WeChatException,RequestException,IOException{
		checkToken(token);
		checkNull(name, "name == null");
		JSONObject grp = new JSONObject();
		grp.put("name", name);
		JSONObject postData = new JSONObject();
		postData.put("group", grp);
		
		RequestParams params = new RequestParams();
		params.setUrl(CREATE_NEW_GROUP_URL)
		.addQuery(ACCESS_TOKEN_NAME, token.getToken())
		.setPostData(postData.toString().getBytes(AppContext.getInstance().getCharset()));
		
		Response resp = HttpUtils.post(params);
		
		String result = resp.getResponseText(AppContext.getInstance().getCharset());
		
		JSONObject jo = check_null_errcode_result(result).getJSONObject("group");
		String nname = jo.getString("name");
		BaseGroup ngroup = new BaseGroup();
		ngroup.setId(jo.getInt("id"));
		ngroup.setName(nname);
		return ngroup;
	}
	public static void deleteGroup(int groupId,AccessToken token) throws WeChatException,RequestException,IOException{
		checkToken(token);
		RequestParams params = new RequestParams(DELETE_GROUP_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		JSONObject data = new JSONObject();
		data.put("id", groupId);
		params.setData(new Data(data.toString().getBytes("utf-8")));
		Response response = HttpUtils.post(params);
		String result = response.getResponseText(AppContext.getInstance().getCharset());
		JSONObject jo = check_zero_errcode_result(result);
		System.out.println(jo);
	}
	/**
	 * 查询所有分组
	 * <hr/>
	 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E5%88%86%E7%BB%84%E7%AE%A1%E7%90%86%E6%8E%A5%E5%8F%A3#.E6.9F.A5.E8.AF.A2.E6.89.80.E6.9C.89.E5.88.86.E7.BB.84">分组管理接口#查询所有分组</a>
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Groups getAllGroups(AccessToken token)throws WeChatException,RequestException,IOException{
		checkToken(token);
		RequestParams params = new RequestParams();
		params.setUrl(GET_ALL_GROUPS_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		Response resp = HttpUtils.get(params);
		
		String result = resp.getResponseText(AppContext.getInstance().getCharset());
		
		JSONObject jo = check_null_errcode_result(result);
		
		@SuppressWarnings("unchecked")
		List<JSONObject> grps = (List<JSONObject>)jo.getJSONArray("groups");
		UserGroup[] groups = new UserGroup[grps.size()];
		int i = 0;
		for(JSONObject m:grps){
			UserGroup grp = new UserGroup();
			grp.setCount(m.getInt("count"));
			grp.setId(m.getInt("id"));
			grp.setName(m.getString("name"));
			groups[i] = grp;
			i++;
		}
		Groups rgps = new Groups();
		rgps.setGroups(groups);
		return rgps;
	}
	/**
	 * 获得某个用户所在分组的ID
	 * <hr/>
	 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E5%88%86%E7%BB%84%E7%AE%A1%E7%90%86%E6%8E%A5%E5%8F%A3#.E6.9F.A5.E8.AF.A2.E7.94.A8.E6.88.B7.E6.89.80.E5.9C.A8.E5.88.86.E7.BB.84">分组管理接口#查询用户所在分组</a>
	 * @param openid
	 * @param token
	 * @return 用户所在的分组ID
	 * @throws Exception
	 */
	public static int getUserGroupId(String openid,AccessToken token)throws WeChatException,RequestException,IOException{
		checkToken(token);
		checkNull(openid, "openid == null");
		JSONObject pdata = new JSONObject();
		pdata.put("openid", openid);
		RequestParams params = new RequestParams(GET_GROUP_ID_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		params.setPostData(pdata.toString().getBytes(AppContext.getInstance().getCharset()));
		
		Response resp = HttpUtils.post(params);
		String result = resp.getResponseText(AppContext.getInstance().getCharset());
		
		JSONObject jo = check_null_errcode_result(result);
		return jo.getInt("groupid");
	}
	/**
	 * 
	 * 更新用户备注名(暂时需要认证)
	 * @param openid
	 * @param remark 新的备注名，长度必须小于30
	 * @param token 调用接口凭证
	 * @date 2015/1/2 15:03
	 * @throws WeChatException
	 * @throws RequestException
	 * @throws IOException
	 */
	public static void updateUserRemark(String openid,String remark,AccessToken token) throws WeChatException,RequestException,IOException{
		checkToken(token);
		checkNull(openid, "openid == null");
		
		RequestParams params = new RequestParams(UPDATE_USER_REMARK_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		params.addQuery("openid", openid);
		
		Response resp = HttpUtils.post(params);
		String result = resp.getResponseText(AppContext.getInstance().getCharset());
		check_zero_errcode_result(result);
	}
	/**
	 * 移动用户分组
	 * <hr/>
	 * 参考文档：<a href="http://mp.weixin.qq.com/wiki/index.php?title=%E5%88%86%E7%BB%84%E7%AE%A1%E7%90%86%E6%8E%A5%E5%8F%A3#.E7.A7.BB.E5.8A.A8.E7.94.A8.E6.88.B7.E5.88.86.E7.BB.84">分组管理接口#移动用户分组</a>
	 * @param openid
	 * @param to_groupid
	 * @param token
	 * @return 0表示移动成功，其它则反 
	 * @throws Exception
	 */
	public static int mvUserGroup(String openid,int to_groupid,AccessToken token)throws WeChatException,RequestException,IOException{
		checkToken(token);
		checkNull(openid, "openid == null");
		
		JSONObject postdata = new JSONObject();
		postdata.put("openid", openid);
		postdata.put("to_groupid", to_groupid);
		
		RequestParams params = new RequestParams(MOVE_USR_GROUP_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		params.setPostData(postdata.toString().getBytes(AppContext.getInstance().getCharset()));
		Response resp = HttpUtils.post(params);
		String result = resp.getResponseText(AppContext.getInstance().getCharset());
		JSONObject jo = check_zero_errcode_result(result);
		
		return jo.getInt(ERRCODE);
	}
	/**
	 * 修改分组名称
	 * <hr/>
	 * @param groupid
	 * @param newName
	 * @param token
	 * @return 错误码，为0表示修改成功，其它的则相反
	 * @throws WeChatException	返回错误码
	 * @throws RequestException
	 * @throws IOException
	 */
	public static int renameUsrGroup(int groupid,String newName,AccessToken token)throws WeChatException,RequestException,IOException{
		checkToken(token);
		checkNull(newName,"newName = null");
		BaseGroup group = new BaseGroup();
		group.setId(groupid);
		group.setName(newName);
		JSONObject postData = new JSONObject();
		postData.put("group", group);
		
		Charset charset = AppContext.getInstance().getCharset(); 
		RequestParams params = new RequestParams(RENAME_USR_GROUP_URL);
		
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken())
		.setPostData(postData.toString().getBytes(charset));
		
		Response resp = HttpUtils.post(params);
		String result = resp.getResponseText(charset);
		
		JSONObject jo = check_zero_errcode_result(result);
		return jo.getInt(ERRCODE);
	}
	/**
	 * 用来检查返回结果中errcode为null才正确的情况
	 */
	private static JSONObject check_null_errcode_result(String result)throws WeChatException{
		JSONObject jo = JSONObject.fromObject(result);
		if(jo.containsKey(ERRCODE)){
			int errorCode = jo.getInt(ERRCODE);
			LOG.error(jo.getString(ERRMSG));
			throw new WeChatException(errorCode,jo);
		}else{
			LOG.info(result);
		}
		return jo;
	}
	/**
	 * 用来检查返回结果中errcode为0才正确的情况
	 */
	private static JSONObject check_zero_errcode_result(String result)throws WeChatException{
		JSONObject jo = JSONObject.fromObject(result);
		int errcode = jo.getInt(ERRCODE);
		if(errcode == 0){
			LOG.info(jo.getString(ERRMSG));
		}else{
			LOG.error(jo.getString(ERRMSG));
			throw new WeChatException(errcode,jo);
		}
		return jo;
	}
	/**
	 * 使用代码创建自定义菜单
	 * @param code 实例：[[主菜单1[click,点击,eventKey]],[主菜单2[view,链接,url]]]
	 * @param accessToken
	 * @throws WeChatException
	 * @throws RequestException
	 * @throws IOException
	 * @throws MenuParseException
	 */
	public static void createMenu(String code,AccessToken accessToken) throws WeChatException,RequestException,IOException,MenuParseException{
		
	}
	private Object[] parseCode(String code) throws MenuParseException{
		
		return null;
	}
	/**
	 * 创建菜单
	 * <hr/>
	 * @param menu 菜单实例
	 * @param accessToken 凭证
	 * @throws WeChatException 创建异常
	 * @throws RequestException 请求异常，可能网络有问题
	 * @throws IOException		数据读取错误
	 */
	public static void createMenu(Menu menu,AccessToken accessToken)throws WeChatException,RequestException,IOException{
		Charset charset = AppContext.getInstance().getCharset();
		RequestParams params = new RequestParams(MENU_CREATE_URL+accessToken.getToken());
		params.setPostData(JSONObject.fromObject(menu).toString().getBytes(charset));
		
		String responseData = HttpUtils.post(params).getResponseText(charset);
		JSONObject jr = JSONObject.fromObject(responseData);
		
		int errcode = jr.getInt(ERRCODE);
		
		if(errcode != 0){
			LOG.error("创建菜单失败："+responseData);
			throw new WeChatException(errcode,jr);
		}
	}
	/**
	 * 查询菜单
	 * @param accessToken	通用接口凭证
	 * @return
	 * @throws WeChatException
	 * @throws RequestException
	 * @throws IOException
	 */
	public static Menu getMenu(AccessToken accessToken)throws WeChatException,RequestException,IOException{
		
		Charset charset = AppContext.getInstance().getCharset();
		RequestParams params = new RequestParams(GET_USER_MENUS_URL);
		params.addQuery(ACCESS_TOKEN_NAME, accessToken.getToken());
		
		String responseData = HttpUtils.post(params).getResponseText(charset);
		
		JSONObject jo = check_null_errcode_result(responseData);
		
		Menu menu = new Menu();
		
		JSONObject jmenu = jo.getJSONObject("menu");
		
		JSONArray jbuttons = jmenu.getJSONArray("button");
		
		List<Button> buttons = parseBtns(jbuttons);
		menu.setButton(buttons.toArray(new Button[buttons.size()]));
		return menu;
	}
	private static List<Button> parseBtns(JSONArray jarr){
		List<Button> buttons = new ArrayList<Button>(jarr.size());
		for(Object item:jarr){
			if(!(item instanceof JSONObject)){
				continue;
			}
			JSONObject jitem = (JSONObject)item;
			Button btn = parseBtn(jitem);
			buttons.add(btn);
		}
		return buttons;
	}
	@SuppressWarnings("deprecation")
	private static Button parseBtn(JSONObject item){
		if(item.containsKey("type")){
			String type = item.getString("type");
			if("click".equals(type)){
				EventButton ebtn = new EventButton();
				ebtn.setKey(item.getString("key"));
				ebtn.setType(type);
				ebtn.setName(item.getString("name"));
				return ebtn;
			}else if("view".equals(type)){
				ViewButton vbtn = new ViewButton();
				vbtn.setType(type);
				vbtn.setName(item.getString("name"));
				vbtn.setUrl(item.getString("url"));
				return vbtn;
			}
		}else{
			Button btn = new ComplexButton();
			btn.setName(item.getString("name"));
			List<Button> btns = parseBtns(item.getJSONArray("sub_button"));
			((ComplexButton)btn).setSub_button(btns.toArray(new Button[btns.size()]));
			return btn;
		}
		return null;
	}
	private static final void checkToken(AccessToken token){
		if(token == null || token.getToken() == null){
			throw new IllegalArgumentException("token == null");
		}
	}
	private static final void checkNull(Object obj,String message){
		if(obj == null){
			throw new IllegalArgumentException(message);
		}
	}
	/**
	 * 删除菜单（全部）
	 * @param token 接口调用凭证
	 * throws WechatException
	 * throws RequestException
	 * throws IOException
	 */
	public static void deleteMenu(AccessToken token) throws WeChatException,RequestException,IOException{
		RequestParams params = new RequestParams(DELETE_MENU_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		Response resp = HttpUtils.get(params);
		String respText = resp.getResponseText(AppContext.getInstance().getCharset());
		check_zero_errcode_result(respText);
	}
	/**
	 * 发送客服消息
	 * @param msg 消息
	 * @param token
	 */
	public static void sendServiceNews(SvBaseMessage msg,AccessToken token)throws RequestException,WeChatException,IOException{
		JSONObject data = JSONObject.fromObject(msg);
		Response response = HttpUtils.post(
				new RequestParams(SEND_SERVICE_NEWS_MESSAGE_URL)
				.addQuery(ACCESS_TOKEN_NAME, token.getToken())
				.setPostData(
						data.toString().getBytes(AppContext.getInstance().getCharset()))
				);
		check_zero_errcode_result(response.getResponseText(AppContext.getInstance().getCharset()));
	}
	/**
	 * oauth 网页授权 获得用户 openid 
	 * @param code			网页上传过来的code（由微信生成）
	 * @param credential	开发者凭证
	 * @return
	 * @throws RequestException	请求微信服务器异常（网络异常）
	 * @throws WeChatException	请求返回错误码
	 * @throws IOException		读取返回消息异常
	 */
	public static String getOpenidByCode(String code,Credential credential) throws RequestException,WeChatException,IOException{
		String openid = null;
		RequestParams params = new RequestParams(GET_OPENID_BY_CODE);
		params.addQuery("appid", credential.getAppId());
		params.addQuery("secret", credential.getAppSecret());
		params.addQuery("code", code);
		params.addQuery("grant_type", "authorization_code");
		Response response = HttpUtils.get(params);
		JSONObject jo = check_null_errcode_result(response.getResponseText(AppContext.getInstance().getCharset()));
		openid = jo.getString("openid");
		return openid;
	}
	/**
	 * 用于获得多媒体文件响应信息
	 * @param media_id 媒体ID
	 * @param token 接口调用凭证
	 * @return
	 * @throws RequestException	请求网络异常
	 */
	public static Response getMediaResponse(String media_id,AccessToken token) throws RequestException{
		RequestParams req = new RequestParams();
		req.setUrl(DOWNLOAD_MEDIA_URL);
		req.addQuery("access_token", token.getToken());
		req.addQuery("media_id", media_id);
		return HttpUtils.get(req);
	}
	
	public static enum UploadType{
		/**图片*/
		image,
		/**语音*/
		voice,
		/**视频*/
		video,
		/** 缩略图*/
		thumb;
	}
	/**
	 * 
	 * 上传多媒体
	 * <p>
	 * <b>注意事项</b>
	 * <p>
	 * 上传的多媒体文件有格式和大小限制，如下：
	 * <ul>
	 *	<li>图片（image）: 128K，支持JPG格式</li>
	 *	<li>语音（voice）：256K，播放长度不超过60s，支持AMR\MP3格式</li>
	 *	<li>视频（video）：1MB，支持MP4格式</li>
	 *	<li>缩略图（thumb）：64KB，支持JPG格式</li>
	 *</ul>
	 * <strong>媒体文件在后台保存时间为3天，即3天后media_id失效。</strong>
	 * </p>
	 * </p>
	 * @param type	多媒体类型
	 * @param token 接口调用凭证
	 * @param media 媒体数据流
	 * @return 接口返回数据
	 * @throws RequestException
	 * @throws IOException 
	 * @throws WeChatException
	 */
	public static UploadResult uploadMedia(UploadType type,AccessToken token,InputStream media,String fileName)throws RequestException,IOException,WeChatException{
		checkToken(token);
		checkNull(type, "type == null");
		
		RequestParams params = new RequestParams(UPLOAD_MEDIA_URL);
		
		params.addQuery("type", type.name());
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		
		Data data = new Data(media);
		
		data.setFileField("media");
		data.setFileName(fileName);
		if(fileName != null && fileName.length() > 0){
			int idx = fileName.lastIndexOf('.');
			if(idx != -1){
				String ext = fileName.substring(idx+1);
				if(isImage(ext)){
					data.setFileType("image/"+ext);
				}else if(isVideo(ext)){
					data.setFileType("video/"+ext);
				}
			}
		}
		params.addHead("Content-Length", media.available()+"");
		params.setData(data);
		
		Response resp = HttpUtils.post(params);
		
		JSONObject result = check_null_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
		
		UploadResult ur = new UploadResult();
		
		ur.setType(result.getString("type"));
		ur.setMedia_id(result.getString("media_id"));
		ur.setCreated_at(result.getInt("created_at"));
		return ur;
	}
	public static boolean isImage(String ext){
		return ext!=null && ext.endsWith("png") || ext.endsWith("jpg")||ext.endsWith("jpeg")||ext.endsWith("gif");
	}
	public static boolean isVideo(String ext){
		return ext!=null && ext.matches(".*\\.(rm)|(rmvb)|(wmv)|(avi)|(mpg)|(mpeg)|(mp4)$");
	}
	
	/**
	 * 上传图文消息素材
	 * @param news	图文消息
	 * @param token	接口调用凭证
	 * @return		接口返回数据
	 * @throws RequestException
	 * @throws IOException
	 * @throws WeChatException
	 */
	public static UploadResult uploadNews(UploadNews news,AccessToken token) throws RequestException,IOException,WeChatException{
		checkToken(token);
		checkNull(news, "news == null");
		RequestParams params = new RequestParams(UPLOAD_NEWS_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		
		params.setPostData(JSONObject.fromObject(news).toString().getBytes(AppContext.getInstance().getCharset()));
		
		Response resp = HttpUtils.post(params);
		
		JSONObject result = check_null_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
		
		UploadResult ur = new UploadResult();
		
		ur.setType(result.getString("type"));
		ur.setMedia_id(result.getString("media_id"));
		ur.setCreated_at(result.getInt("created_at"));
		
		return ur;
	}
	/**
	 * 获得群发视频的媒体id
	 * @see 
	 * @param video	包含了上传接口产生的媒体id
	 * @param token	接口调用凭证
	 * @return		接口返回数据,包含用于群发视频消息的媒体id
	 * @throws RequestException
	 * @throws IOException
	 * @throws WeChatException
	 */
	public static MassVideoInfo getMassVideoMediaId(VideoInfo video,AccessToken token) throws RequestException,IOException,WeChatException{
		checkToken(token);
		checkNull(video, "video == null");
		RequestParams params = new RequestParams(MASS_UPLOAD_VIDEO_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		
		params.setPostData(JSONObject.fromObject(video).toString().getBytes(AppContext.getInstance().getCharset()));
		
		Response resp = HttpUtils.post(params);
		
		JSONObject result = check_null_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
		
		MassVideoInfo ur = new MassVideoInfo();
		
		ur.setType(result.getString("type"));
		ur.setMedia_id(result.getString("media_id"));
		ur.setCreated_at(result.getInt("created_at"));
		
		return ur;
	}
	/**
	 * 根据分组群发消息（不包括视频消息）
	 * @param groupid
	 * @param body
	 * @param token
	 * @return msg_id
	 * @throws RequestException
	 * @throws IOException
	 * @throws WeChatException
	 */
	public static int sendAll(int groupid,MassMsgBody body,AccessToken token) throws RequestException,IOException,WeChatException{
		checkToken(token);
		checkNull(body, "body == null");
		
		MassGroupMsg msg = null;
		if(body.getClass() == MassImage.class){
			MassGroupImageMsg _msg = new MassGroupImageMsg();
			_msg.setImage((MassImage)body);
			msg = _msg;
		}else if(body.getClass() == MassMPNews.class){
			MassGroupNewsMsg _msg = new MassGroupNewsMsg();
			_msg.setMpnews((MassMPNews)body);
			msg = _msg;
		}else if(body.getClass() == MassVoice.class){
			MassGroupVoiceMsg _msg = new MassGroupVoiceMsg();
			_msg.setVoice((MassVoice)body);
			msg = _msg;
		}else if(body.getClass() == MassText.class){
			MassGroupTextMsg _msg = new MassGroupTextMsg();
			_msg.setText((MassText)body);
			msg = _msg;
		}else{
			throw new IllegalArgumentException("unsupported body type : "+body.getClass().getName());
		}
		msg.setFilter(new MassFilter(groupid));
		
		RequestParams params = new RequestParams(MASS_SEND_ALL_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		params.setPostData(JSONObject.fromObject(msg).toString().getBytes(AppContext.getInstance().getCharset()));
		
		Response resp = HttpUtils.post(params);
		
		JSONObject result = check_zero_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
		return result.getInt("msg_id");
	}
	/**
	 * 发送预览
	 * @param touser
	 * @param body
	 * @param token
	 * @return
	 * @throws WeChatException
	 * @throws IOException
	 * @throws RequestException
	 */
	public static void preview(String touser,MassMsgBody body,AccessToken token) throws WeChatException,IOException,RequestException{
		checkToken(token);
		checkNull(body, "body == null");
		if(touser == null || touser.length() < 1){
			throw new IllegalArgumentException("消息接收用户为空");
		}
		MassPreviewMsg msg = null;
		if(body.getClass() == MassImage.class){
			MassPreviewImageMsg _msg = new MassPreviewImageMsg();
			_msg.setImage((MassImage)body);
			msg = _msg;
		}else if(body.getClass() == MassMPNews.class){
			MassPreviewNewsMsg _msg = new MassPreviewNewsMsg();
			_msg.setMpnews((MassMPNews)body);
			msg = _msg;
		}else if(body.getClass() == MassMPVideo.class){
			MassPreviewVideoMsg _msg = new MassPreviewVideoMsg();
			_msg.setMpvideo((MassMPVideo)body);
			msg = _msg;
		}else if(body.getClass() == MassVoice.class){
			MassPreviewVoiceMsg _msg = new MassPreviewVoiceMsg();
			_msg.setVoice((MassVoice)body);
			msg = _msg;
		}else if(body.getClass() == MassText.class){
			MassPreviewTextMsg _msg = new MassPreviewTextMsg();
			_msg.setText((MassText)body);
			msg = _msg;
		}else{
			throw new IllegalArgumentException("unsupported body type : "+body.getClass().getName());
		}
		msg.setTouser(touser);
		RequestParams params = new RequestParams(MASS_PREVIEW_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		params.setPostData(JSONObject.fromObject(msg).toString().getBytes(AppContext.getInstance().getCharset()));
		
		Response resp = HttpUtils.post(params);
		
		JSONObject result = check_zero_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
	}
	/**
	 * 根据openid列表群发消息（除视频消息）<br/>
	 * 发送视频消息:{@link #sendVideo(List, MassVideoInfo, AccessToken)}
	 * @param touser openid列表
	 * @param body
	 * @param token
	 * @return msg_id
	 * @throws RequestException
	 * @throws IOException
	 * @throws WeChatException
	 */
	public static int send(List<String> touser,MassMsgBody body,AccessToken token)  throws RequestException,IOException,WeChatException{
		checkToken(token);
		checkNull(body, "body == null");
		if(touser == null || touser.size() < 1){
			throw new IllegalArgumentException("未提供群发消息接收对象");
		}
		MassOPListMsg msg = null;
		if(body.getClass() == MassImage.class){
			MassOPListImageMsg _msg = new MassOPListImageMsg();
			_msg.setImage((MassImage)body);
		}else if(body.getClass() == MassMPNews.class){
			MassOPListNewsMsg _msg = new MassOPListNewsMsg();
			_msg.setMpnews((MassMPNews)body);
			msg = _msg;
		}else if(body.getClass() == MassVoice.class){
			MassOPListVoiceMsg _msg = new MassOPListVoiceMsg();
			_msg.setVoice((MassVoice)body);
			msg = _msg;
		}else if(body.getClass() == MassText.class){
			MassOPListTextMsg _msg = new MassOPListTextMsg();
			_msg.setText((MassText)body);
			msg = _msg;
		}else{
			throw new IllegalArgumentException("unsupported body type : "+body.getClass().getName());
		}
		msg.setTouser(touser);
		RequestParams params = new RequestParams(MASS_SEND_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		params.setPostData(JSONObject.fromObject(msg).toString().getBytes(AppContext.getInstance().getCharset()));
		
		Response resp = HttpUtils.post(params);
		
		JSONObject result = check_zero_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
		return result.getInt("msg_id");
	} 
	/**
	 * 按分组群发视频消息
	 * @see #getMassVideoMediaId(VideoInfo, AccessToken)
	 * @param groupid
	 * @param mvi
	 * @param token
	 * @return msg_id
	 * @throws RequestException
	 * @throws IOException
	 * @throws WeChatException
	 */
	public static int sendVideoAll(int groupid,MassVideoInfo mvi,AccessToken token)  throws RequestException,IOException,WeChatException{
		MassGroupVideoMsg vmsg = new MassGroupVideoMsg();
		vmsg.setFilter(new MassFilter(groupid));
		vmsg.setMpvideo(new MassMPVideo(mvi.getMedia_id()));
		
		RequestParams params = new RequestParams(MASS_SEND_ALL_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		params.setPostData(JSONObject.fromObject(vmsg).toString().getBytes(AppContext.getInstance().getCharset()));
		Response resp = HttpUtils.post(params);
		JSONObject result = check_zero_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
		return result.getInt("msg_id");
	}
	/**
	 * 按openid列表群发视频消息
	 * @see WeChatUtil#getMassVideoMediaId(VideoInfo, AccessToken)
	 * @param touser
	 * @param mvi
	 * @param token
	 * @return msg_id {@link #}
	 * @throws RequestException
	 * @throws IOException
	 * @throws WeChatException
	 */
	public static int sendVideo(List<String> touser,MassVideoInfo mvi,AccessToken token)  throws RequestException,IOException,WeChatException{
		checkNull(mvi, "video info is null");
		checkToken(token);
		if(touser == null || touser.size() < 1){
			throw new IllegalArgumentException("未提供群发消息接收对象");
		}
		MassOPListVideoMsg vmsg = new MassOPListVideoMsg();
		vmsg.setTouser(touser);
		vmsg.setMpvideo(new MassMPVideo(mvi.getMedia_id()));
		
		RequestParams params = new RequestParams(MASS_SEND_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		params.setPostData(JSONObject.fromObject(vmsg).toString().getBytes(AppContext.getInstance().getCharset()));
		Response resp = HttpUtils.post(params);
		JSONObject result = check_zero_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
		return result.getInt("msg_id");
	}
	/**
	 * 多媒体下载
	 * @param mediaId
	 * @param token
	 * @return
	 * @throws WeChatException
	 * @throws RequestException
	 * @throws IOException
	 */
	public static MediaData downloadMediaById(String mediaId,AccessToken token) throws WeChatException,RequestException,IOException{
		Response resp = getMediaResponse(mediaId, token);
		ReadOnlyConnection conn = resp.getConnection();
		String contentType = conn.getContentType();
		if(StringUtils.equalsIgnoreCase(contentType, "text/plain")){
			String response = resp.getResponseText(AppContext.getInstance().getCharset());
			JSONObject jo = JSONObject.fromObject(response);
			throw new WeChatException(jo.getInt(ERRCODE),jo);
		}
		String cd = conn.getHeaderField("Content-disposition");
		String fileName = StringUtils.substringBetween(cd, "\"", "\"");
		String sdate = conn.getHeaderField("Date");
		MediaData md = new MediaData();
		
		@SuppressWarnings("deprecation")
		long d = Date.parse(sdate);
		
		md.setDate(new Date(d));
		
		md.setFileName(fileName);
		md.setContentLength(conn.getContentLength());
		md.setStream(resp.getStream());
		return md;
	}
	/**
	 * 删除群发
	 * <p style='font-weight:bold;font-family:Ubunto'>
	 * 请注意，只有已经发送成功的消息才能删除删除消息只是将消息的图文详情页失效，
	 * <br/>已经收到的用户，还是能在其本地看到消息卡片。 另外，删除群发消息只能删除
	 * <br/>图文消息和视频消息，其他类型的消息一经发送，无法删除
	 * </p>
	 * @param msgid
	 * @param token
	 * @throws WeChatException
	 * @throws RequestException
	 * @throws IOException
	 */
	public static void deleteMasss(int msgid,AccessToken token) throws WeChatException,RequestException,IOException{
		JSONObject json = new JSONObject();
		json.put("msg_id", msgid);
		RequestParams params = new RequestParams(MASS_DELETE_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		params.setPostData(json.toString().getBytes(AppContext.getInstance().getCharset()));
		
		check_zero_errcode_result(HttpUtils.post(params).getResponseText(AppContext.getInstance().getCharset()));
	}
	/**
	 * 通过url下载图片
	 * @param url
	 * @return
	 * @throws RequestException 
	 */
	public static URLMediaData downloadMediaFromURL(String url) throws RequestException{
		Response resp = HttpUtils.get(new RequestParams(url));
		URLMediaData idata = new URLMediaData();
		int size = getImageSize(url);
		idata.setPicSize(size);
		idata.setContentLength(resp.getConnection().getContentLength());
		idata.setStream(resp.getStream());
		try {
			String p = new URL(url).getPath();
			idata.setFileName(p.substring(StringUtils.indexOf(p, "/", 2)+1,p.lastIndexOf('/')));
		} catch (MalformedURLException e) {
			idata.setFileName(UUID.randomUUID().toString());
		}
		return idata;
	}
	/**
	 * 创建临时二维码Ticket
	 * @param token		接口调用凭证
	 * @param sceneId	场景值ID，临时二维码时为32位非0整型。
	 * @param expireSeconds	该二维码有效时间，以秒为单位。 最大不超过1800。
	 * @throws RequestException
	 * @throws WeChatException
	 * @throws IOException
	 * 
	 */
	public static Ticket createTemporaryQRCode(int sceneId,int expireSeconds,AccessToken token) throws WeChatException,RequestException,IOException{
		if(expireSeconds < 0 || expireSeconds > 1800){
			throw new IllegalArgumentException("expire_seconds out of bounds[0 1800]:"+expireSeconds);
		}
		RequestParams params = new RequestParams(CREATE_QRCODE_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		JSONObject jo = new JSONObject();
		jo.put("expire_seconds", expireSeconds);
		jo.put("action_name", "QR_SCENE");
		JSONObject aifo = new JSONObject();
		JSONObject scene = new JSONObject();
		scene.put("scene_id", sceneId);
		aifo.put("scene", scene);
		jo.put("action_info", aifo);
		params.setPostData(jo.toString().getBytes(AppContext.getInstance().getCharset()));
		
		Response resp = HttpUtils.post(params);
		
		JSONObject result = check_null_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
		Ticket ticket = new Ticket();
		ticket.setExpire_seconds(result.getInt("expire_seconds"));
		ticket.setTicket(result.getString("ticket"));
		return ticket;
	}
	/**
	 * 创建永久二维码Ticket
	 * @param token
	 * @param sceneId	场景值ID，永久二维码最大值为100000（目前参数只支持1--100000）
	 * @throws RequestException
	 * @throws WeChatException
	 * @throws IOException
	 * @return 
	 */
	public static Ticket createPermanentQRCode(int sceneId,AccessToken token) throws WeChatException,RequestException,IOException{
		if(sceneId < 1 || sceneId > 100000){
			throw new IllegalArgumentException("sceneId out of bounds[1,100000]:"+sceneId);
		}
		RequestParams params = new RequestParams(CREATE_QRCODE_URL);
		params.addQuery(ACCESS_TOKEN_NAME, token.getToken());
		JSONObject jo = new JSONObject();
		jo.put("action_name", "QR_SCENE");
		JSONObject aifo = new JSONObject();
		JSONObject scene = new JSONObject();
		scene.put("scene_id", sceneId);
		aifo.put("scene", scene);
		jo.put("action_info", aifo);
		params.setPostData(jo.toString().getBytes(AppContext.getInstance().getCharset()));
		
		Response resp = HttpUtils.post(params);
		
		JSONObject result = check_null_errcode_result(resp.getResponseText(AppContext.getInstance().getCharset()));
		Ticket ticket = new Ticket();
		ticket.setExpire_seconds(result.getInt("expire_seconds"));
		ticket.setTicket(result.getString("ticket"));
		return ticket;
	}
	
	/**
	 * 通过ticket换取二维码
	 * @param ticket 二维码ticket 可以从 {@link #createTemporaryQRCode(int, int, AccessToken)}或 {@link #createPermanentQRCode(int, AccessToken)}获得
	 * @return 二维码图片数据流。错误情况下如（ticket非法）则返回空或抛出异常
	 * @throws RequestException
	 * @throws IOException
	 */
	public static ImageInputStream showQRCode(Ticket ticket) throws RequestException,IOException{
		if(ticket == null){
			throw new IllegalArgumentException("ticket == null");
		}
		RequestParams params = new RequestParams(SHOW_QRCODE_URL);
		params.addQuery("ticket", ticket.getTicket());
		
		Response resp = HttpUtils.post(params);
		
		if(resp.getStatusCode() == 200){
			ImageInputStream ii = ImageIO.createImageInputStream(resp.getStream());
			return ii;
		}else{
			return null;
		}
	}
	/**
	 * 授权作用域
	 * @author yangjianxin
	 * @date 2015-1-9 下午12:57:30
	 * @see WeChatUtil#createAuthUrl(String, String, String, AuthScope)
	 */
	public static enum AuthScope{
		/**
		 * 不弹出授权页面，直接跳转，但只能获取openid
		 */
		SNSAPI_BASE,
		/**
		 * 弹出授权页面，可以通过openid拿到昵称、性别、所在地。并且在用户未关注的情况下，只要用户授权，也能获取其信息
		 */
		SNSAPI_USERINFO
	}
	/**
	 * 构造授权页面地址
	 * @param appid 公众号标识
	 * @param redirectUrl 重定向的回调链接地址
	 * @param state	参数 只能是a-zA-Z0-9的值 可以不填
	 * @param scope 授权作用域
	 * @return
	 */
	public static final String createAuthUrl(String appid,String redirectUrl,String state,AuthScope scope){
		StringBuilder sb = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		sb.append(appid);
		sb.append("&redirect_uri=");
		try {
			sb.append(URLEncoder.encode(redirectUrl,"UTF-8"));
		} catch (UnsupportedEncodingException e) {}
		
		sb.append("&response_type=code&scope="+(scope==null?AuthScope.SNSAPI_BASE:scope).name().toLowerCase());
		if(state != null){
			if(state.matches("[a-zA-Z0-9]+")){
				sb.append("&state=");
				sb.append(state);
			}else{
				throw new IllegalArgumentException("state参数只能是a-zA-Z0-9的值");
			}
		}
		sb.append("#wechat_redirect");
		return sb.toString();
	}
}
