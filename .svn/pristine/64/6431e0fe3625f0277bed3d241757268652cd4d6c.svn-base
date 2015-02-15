package y1j2x34.wechat.state.model;

import java.io.Serializable;
/**
 * 
 * 用于记录某事物当前状态，
 * @author yangjianxin
 * @date 2014-11-28 上午10:19:00
 * @param <Owner> 状态持有者
 */
public class CurrentStateModel<Owner> implements Serializable{
	private static final long serialVersionUID = 1L;
	// unique key
	private Owner ownerId;
	private Integer currentStateId;
	public CurrentStateModel() {
	}
	public CurrentStateModel(Owner ownerId, Integer currentStateId) {
		this.ownerId = ownerId;
		this.currentStateId = currentStateId;
	}
	public Owner getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Owner ownerId) {
		this.ownerId = ownerId;
	}
	
	public Integer getCurrentStateId() {
		return currentStateId;
	}
	public void setCurrentStateId(Integer currentStateId) {
		this.currentStateId = currentStateId;
	}
}
