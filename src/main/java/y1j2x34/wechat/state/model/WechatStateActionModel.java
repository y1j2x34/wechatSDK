package y1j2x34.wechat.state.model;

import java.io.Serializable;

public class WechatStateActionModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer actionId;
	private String actionName;
	private String entityName;
	
	public WechatStateActionModel() {
	}
	
	public WechatStateActionModel(Integer actionId) {
		super();
		this.actionId = actionId;
	}

	public WechatStateActionModel(Integer actionId,String actionName, String entityName) {
		super();
		this.actionId = actionId;
		this.actionName = actionName;
		this.entityName = entityName;
	}
	public Integer getActionId() {
		return actionId;
	}
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
}
