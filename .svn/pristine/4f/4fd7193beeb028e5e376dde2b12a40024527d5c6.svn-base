package y1j2x34.wechat;
/**
 * 目前封装好的微信API名称
 * @see WeChatAPILogger
 * @author yangjianxin
 * @date 2014-12-10 下午4:37:09
 */
public enum API {
	GET_ACCESS_TOKEN("获取接口调用凭证"),
	GET_CONCEREND_USERS("获取关注的用户列表"),
	GET_WECHAT_USER_INFO("获取微信用户信息"),
	GET_ALL_GROUPS("查询所有分组"),
	GET_USER_GROUP_ID("查询用户所在分组ID"),
	GET_MENU("查询菜单"),
	CREATE_GROUP("创建分组"),
	MV_USER_GROUP("移动用户分组"),
	RENAME_USER_GROUP("修改分组名称"),
	CREATE_MENU("创建菜单"),
	DELETE_MENU("删除菜单"),
	SEND_SERVICE_NEWS("发送客服消息"),
	GET_OPENID_BY_OAUTH_CODE("通过OAuth网页授权获取用户openid"),
	GET_MEDIA_RESPONSE("获得多媒体文件相应信息"),
	UPLOAD_MEDIA("上传多媒体"),
	UPLOAD_NEWS("上传图文消息素材"),
	GET_MASS_VIDEO_MEDIA_ID("获得群发视频的媒体ID"),
	SEND_MASS_WITH_GROUP("按分组群发消息"),
	SEND_MASS_WITH_OPENID_LIST("按openid列表群发消息"),
	SEND_MASS_VIDEO_WITH_GROUP("按分组群发视频消息"),
	SEND_MASS_VIDEO_WITH_OPENID_LIST("按openid列表群发视频消息"),
	DOWNLOAD_MEDIA("多媒体下载"),
	/**取消群发消息*/
	DELETE_MASS("删除群发"),
	CREATE_TEMPORARY_QRCODE("创建临时二维码Ticket"),
	CREATE_PERMANENT_QRCODE("创建永久二维码Ticket"),
	GET_QRCODE("通过Ticket换取二维码")
	;
	private String chineseName;
	
	API(String chineseName){
		this.chineseName = chineseName;
	}
	
	public String getChineseName() {
		return chineseName;
	}
}
