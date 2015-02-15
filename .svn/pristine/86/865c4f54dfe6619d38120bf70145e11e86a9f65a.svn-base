package y1j2x34.wechat.state;

import java.util.List;

//import com.upower.wechat.state.model.CurrentStateModel;

import y1j2x34.wechat.state.model.CurrentStateModel;
import y1j2x34.wechat.state.model.WechatStateCondition;
import y1j2x34.wechat.state.model.WechatStateModel;
import y1j2x34.wechat.state.model.WechatStateActionModel;
import y1j2x34.wechat.state.model.WechatStateMapping;

public interface WechatStateRepository<Owner> {
	
	WechatStateModel getStateModel(Integer stateId);
	
	CurrentStateModel<Owner> getCurrentStateModel(Owner owner);
	
	void init(Owner owner);
	
	void destroy(Owner owner);
	
	List<WechatStateMapping> getMappings(Owner ownerId,Integer fromStateId);
	
	WechatStateActionModel getActionModel(Integer actionId);
	
	WechatStateCondition getStateCondition(Integer conditionId);
	
	Object getAction(String entity);
	
	void updateState(Owner owner, Integer stateId);
	
	WechatStateMatcher<? extends Object> createMatcher(String name,String value);
	
	void reset(Owner owner);
}
