package y1j2x34.wechat.state;

import java.util.HashMap;
import java.util.Map;

import y1j2x34.wechat.state.model.CurrentStateModel;
import y1j2x34.wechat.state.model.WechatStateModel;
import y1j2x34.wechat.state.model.WechatStateActionModel;

class InternalWechatStateMachine<Owner> extends WechatStateMachine implements WechatStateListener{
	private WechatStateRepository<Owner> repository;
	private Owner ownerId;
	private Map<Integer,State> cache = new HashMap<Integer,State>();
	protected InternalWechatStateMachine(WechatStateRepository<Owner> rep,Owner ownerId) {
		this.repository = rep;
		this.ownerId = ownerId;
		super.wechatStateListener = this;
	}
	State getState(Integer stateId) throws WechatStateException{
		if(cache.containsKey(stateId)){
			return cache.get(stateId);
		}
		WechatStateModel wsm = repository.getStateModel(stateId);
		if(wsm == null){
			throw new WechatStateException("状态不存在！编号为："+stateId);
		}
		WechatStateActionModel action = repository.getActionModel(wsm.getActionId());
		Object actionEntity = repository.getAction(action.getEntityName());
		State s = new InternalState<Owner>(this, wsm.getStateName(), actionEntity,ownerId,wsm.getStateId(),wsm.getStateType(),repository);
		return s;
	}
	public State transition(Object cond) throws WechatStateException{
		State next = current != null?current.transition(cond):null;
		if(next != null){
			try{
				if(wechatStateListener != null){
					wechatStateListener.afterStateChange(this, current, next, cond);
				}
			}finally{
				if(State.PERSISTENCE_TYPE.equals(next.getType())){
					return next;
				}
				current = next;
				repository.updateState(ownerId, current.stateId);
			}
		}
		return current;
	}
	@Override
	public boolean can(Object cond) {
		return current != null && current.can(cond);
	}
	public boolean can(State to){
		return current != null && current.can(to);
	}
	public boolean cannot(State to){
		return !can(to);
	}
	
	public void reset(){
		repository.reset(ownerId);
		CurrentStateModel<Owner> curr = repository.getCurrentStateModel(ownerId);
		try {
			current = getState(curr.getCurrentStateId());
		} catch (WechatStateException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void afterStateChange(WechatStateMachine machine, State preview,
			State next, Object cond) {
	}
}
