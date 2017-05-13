#微信公众平台SDK Java

##内容：
1. web.xml配置
2. spring 配置
3. 接收消息
4. 拦截器
  - 重试消息拦截
  - 一般消息拦截
  - 异常处理
  - 消息处理结果
5. 接口调用

---

### 1. web.xml配置
```
<!-- 全局变量  -->
<context-param>
  <!-- 微信数据编码. 非必须，默认为utf-8 -->
  <param-name>encoding</param-name>
  <param-value>utf-8</param-value>
</context-param>
<context-param>
  <!-- 提前刷新accessToken时间，单位：ms，默认值为0，设置太小不能保证每次调用接口用的access_token是有效的  -->
  <param-name>tokenAdvance</param-name>
  <param-value>200</param-value>
</context-param>
<!-- 监听器  -->
<listener>
  	<listener-class>y1j2x34.wechat.WechatPrepareListener</listener-class>
</listener>
```
### 2. spring 配置
```
<bean class="y1j2x34.wechat.WechatCoreService" init-method="onCreate" destroy-method="onDestroy" id="wechatCoreService">
		  <property name="processor" ref="myProcessor"/><!-- 消息处理器  -->
		  <property name="interceptors"><!-- 拦截器  -->
		    <list>
		      <ref bean="wechat-interceptor"/>
		    </list>
		  </property>
</bean>	
```
### 3. 接收消息

```
//Credential
public class MyCredential extends Credential{
	public String getAppId(){
		return "appid";
	}
	public String getAppSecret(){
		return "appSecret"
	}
}
```

```
//SpringMVC Controller
@Controller
@RequestMapping("xxx.do")
public class WechatCoreAction{
  private WechatCoreService service;
  public void setService(WechatCoreService service){
    this.service = service;
  }
  @RequestMapping(method=RequestMethod.GET)
  public void access(HttpServletRequest req,PrintWriter pw) throws Exception{
    String token = "your token";
    Credential c = new MyCredential();
    String echostr = service.access(token,req);
    if(echostr != null){
    	pw.print(echostr);
    }else{
    	//...
    }
  }
  @RequestMapping(method=RequestMethod.POST)
  public void doMsg(HttpServletRequest req,HttpServletResponse resp) throws Exception{
  	Credential c = new MyCredential();
  	Context context = new DefaultContext();
  	service.doMsg(req, resp, context);
  }
}
spring 配置Action
<bean class="xxx.WechatCoreAction">
	<property name="service" ref="wechatCoreService"/>
</bean>
```
```
//消息处理器
public class MyWechatMessageProcessor implements WechatMessageProcessor{
  public RespBaseMessage execute(ReqBaseMessage message, Context context)
			throws UnknownMsgTypeException {
	  String name = context.get("name");
	  Integer number = context.get("number");
	  RespTextMessage respMsg = new RespTextMessage();
	  MessageUtils.buildRespMsg(message,respMsg);
	  respMsg.setContext("Halou wode!");
	  return respMsg;
	}
}
spring 配置
<bean class="packagename.MyWechatMessageProcessor" id="myProcessor">
  ...
</bean>
```

### 4. 拦截器
```
public class MessageInterceptor implements WechatInterceptor{
	//处理消息前调用这里
	@Override
	public boolean preHandle(ReqBaseMessage reqMessage,
			HttpServletRequest request, HttpServletResponse response,
			Context context) {
		return true;//返回false表示拦截此条微信消息
	}
	//消息处理完后调用这里，此时消息还没有回复给微信用户
	@Override
	public void postHandle(ReqBaseMessage reqMessage,
			RespBaseMessage respMessage, HttpServletRequest request,
			HttpServletResponse response, Context context) {
	}
	//重试的消息会进入这里
	@Override
	public RespBaseMessage duplicateHandle(DuplicateInfo info,
			Context context) {
		//返回空值表示不拦截该重试消息
		//返回RespBaseMessage.NONE表示不处理重试消息
		//返回info.getFirstMsg() 表示返回第一次的处理结果。可能第一次请求没处理完就接收到重试消息，这个值就为空了
		//其它
		return null;
	}
	//消息处理完后调用这里
	@Override
	public void afterComplection(Context context, Exception ex) {
	}
}
```
### 5. 接口调用

1.获取access_token
```
	//access_token过期会自动刷新
	AccessToken accessToken = AppContext.getInstance().getAccessToken(credential);
```
2.删除菜单
```
	WeChatUtils.deleteMenu(AppContext.getInstance().getAccessToken(credential));	
```
3.……
