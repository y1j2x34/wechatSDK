#微信公众平台SDK

##内容：
1. web.xml配置
2. spring 配置
3. 接收消息
4. 拦截器
  1. 重试消息拦截
  2. 一般消息拦截
  3. 异常处理
  4. 消息处理结果
5. 接口调用

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
<bean class="com.upower.wechat.WechatCoreService" init-method="onCreate" destroy-method="onDestroy">
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

####SpringMVC Controller
```
@Controller
@RequestMapping("xxx.do")
public class WechatCoreAction{
  private WechatCoreService service;
  public void setService(WechatCoreService service){
    this.service = service;
  }
  @RequestMapping(method=RequestMethod.GET)
  public void access(HttpServletResponse resp) throws Exception{
    String token = "your token";
    
    service.access;
  }
}

```
