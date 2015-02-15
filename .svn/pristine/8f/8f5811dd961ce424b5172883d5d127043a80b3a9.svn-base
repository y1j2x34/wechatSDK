package y1j2x34.wechat.state;

import java.util.List;

import y1j2x34.wechat.state.model.WechatStateCondition;
import y1j2x34.wechat.state.model.WechatStateMapping;

class InternalState<Owner> extends State{
	private WechatStateRepository<Owner> repository;
	private Owner ownerId;
	InternalState(WechatStateMachine machine, String name,Object action,Owner ownerId,Integer stateId,Integer type,WechatStateRepository<Owner> repsitory) {
		super(machine, name,action,stateId,type);
		this.repository = repsitory;
		this.ownerId = ownerId;
	}
	@Override
	boolean can(State to) {
		List<WechatStateMapping> mappings = repository.getMappings(ownerId, stateId);
		if(mappings != null && mappings.size() > 0){
			for(WechatStateMapping mapping:mappings){
				if(to.stateId.equals(mapping.getToStateId())){
					return true;
				}
			}
		}
		return false;
	}
	@Override
	State transition(Object cond) throws WechatStateException{
		List<WechatStateMapping> mappings = repository.getMappings(ownerId, stateId);
		if(mappings != null && mappings.size() > 0){
			for(WechatStateMapping mapping:mappings){
				Integer conditionId = mapping.getId().getConditionId();
				Integer frmId = mapping.getId().getFromStateId();
				if(!stateId.equals(frmId)){
					continue;
				}
				WechatStateCondition condition = repository.getStateCondition(conditionId);
				if(condition == null){
					continue;
				}else{
					@SuppressWarnings("unchecked")
					WechatStateMatcher<Object> matcher = (WechatStateMatcher<Object>) repository.createMatcher(condition.getMatcherName(), condition.getValue());
					if(matcher != null && matcher.match(cond)){
						return machine.getState(mapping.getToStateId());
					}
				}
			}
			throw new WechatStateException("state cannot transition by condition :"+cond);
		}else{
			throw new WechatStateException("no state mapping!");
		}
	}
	@Override
	boolean can(Object cond) {
		List<WechatStateMapping> mappings = repository.getMappings(ownerId, stateId);
		if(mappings != null && mappings.size() > 0){
			for(WechatStateMapping mapping:mappings){
				Integer conditionId = mapping.getId().getConditionId();
				Integer frmId = mapping.getId().getFromStateId();
				if(!stateId.equals(frmId)){
					continue;
				}
				WechatStateCondition condition = repository.getStateCondition(conditionId);
				if(condition == null){
					continue;
				}else{
					@SuppressWarnings("unchecked")
					WechatStateMatcher<Object> matcher = (WechatStateMatcher<Object>) repository.createMatcher(condition.getMatcherName(), condition.getValue());
					if(matcher != null && matcher.match(cond)){
						return true;
					}
				}
			}
		}
		return false;
	}
}
