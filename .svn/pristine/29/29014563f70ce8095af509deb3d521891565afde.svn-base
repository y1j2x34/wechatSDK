package y1j2x34.wechat.state;

import y1j2x34.wechat.state.model.CurrentStateModel;

public abstract class WechatStateMachine{
	protected State current;
	protected WechatStateListener wechatStateListener;
	
	public static <Owner> WechatStateMachine getInstance(Owner ownerId,final WechatStateRepository<Owner> rep) throws WechatStateException{
		rep.init(ownerId);
		CurrentStateModel<Owner> csm = rep.getCurrentStateModel(ownerId);
		if(csm == null){
			throw new WechatStateException("初始状态未定义！状态机持有人:"+ownerId);
		}
		final WechatStateMachine machine = new InternalWechatStateMachine<Owner>(rep,ownerId);
		machine.initial(csm.getCurrentStateId());
		return machine;
	}
	
	public abstract State transition(Object cond) throws WechatStateException;
	
	public abstract boolean can(Object cond);
	public boolean cannot(Object cond){
		return !can(cond);
	}
	public abstract boolean can(State to);
	public abstract boolean cannot(State to);
	public abstract void reset();
	abstract State getState(Integer stateId) throws WechatStateException;
	public State getCurrent(){
		return current;
	}
	void initial(int stateId) throws WechatStateException{
		this.current = getState(stateId);
	}
	public void setWechatStateListener(WechatStateListener wechatStateListener) {
		this.wechatStateListener = wechatStateListener;
	}
	public WechatStateListener getWechatStateListener() {
		return wechatStateListener;
	}
}
