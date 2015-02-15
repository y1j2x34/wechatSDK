package y1j2x34.wechat.state.model;

import java.io.Serializable;

public class WechatStateMappingId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer fromStateId;
	private Integer conditionId;
	
	public WechatStateMappingId() {
	}
	public WechatStateMappingId(Integer fromStateId,
			Integer conditionId) {
		super();
		this.fromStateId = fromStateId;
		this.conditionId = conditionId;
	}
	public Integer getFromStateId() {
		return fromStateId;
	}
	public void setFromStateId(Integer fromStateId) {
		this.fromStateId = fromStateId;
	}
	public Integer getConditionId() {
		return conditionId;
	}
	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}
}
