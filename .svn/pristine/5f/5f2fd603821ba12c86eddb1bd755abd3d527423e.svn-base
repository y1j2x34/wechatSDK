package y1j2x34.wechat.state;

import java.util.LinkedList;
import java.util.List;

public abstract class State {
	public static final Integer NORMAL_TYPE = 0;
	public static final Integer PERSISTENCE_TYPE = 1;
	
	private final List<Entry> entries = new LinkedList<Entry>();
	protected final WechatStateMachine machine;
	private final String name;
	private final Object action;
	protected final Integer stateId;
	private Integer type;
	
	public State(WechatStateMachine machine,String name,Object action,Integer stateId,Integer type) {
		this.machine = machine;
		this.name = name;
		this.action = action;
		this.stateId = stateId;
		this.type = type;
	}
	abstract State transition(Object cond) throws WechatStateException;
	
	public State round(WechatStateMatcher<Object> matcher){
		return make(matcher, this);
	}
	
	public State make(WechatStateMatcher<Object> matcher,State to){
		entries.add(new Entry(matcher, to));
		return this;
	}
	protected State findNext(Object cond){
		for(Entry entry:entries){
			if(entry.matcher != null && entry.matcher.match(cond)){
				return entry.state;
			}
		}
		return null;
	}
	protected class Entry{
		private WechatStateMatcher<Object> matcher;
		private State state;
		public Entry(WechatStateMatcher<Object> matcher,State state) {
			this.matcher = matcher;
			this.state = state;
		}
		public WechatStateMatcher<?> getMatcher() {
			return matcher;
		}
		public State getState() {
			return state;
		}
	}
	public Integer getStateId() {
		return stateId;
	}
	public String getName() {
		return name;
	}
	public Integer getType() {
		return type;
	}
	public WechatStateMachine getMachine() {
		return machine;
	}
	@SuppressWarnings("unchecked")
	public <T> T getAction() {
		return (T)action;
	}
	abstract boolean can(State to);
	
	abstract boolean can(Object cond);
}
