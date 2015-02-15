package y1j2x34.wechat.state;

import java.util.HashMap;
import java.util.Map;

import y1j2x34.wechat.state.model.WechatStateModel;

public class WechatStateMap<Owner> extends HashMap<Owner,Map<Integer,WechatStateModel>> implements Map<Owner,Map<Integer,WechatStateModel>>{

	private static final long serialVersionUID = 1L;

}
